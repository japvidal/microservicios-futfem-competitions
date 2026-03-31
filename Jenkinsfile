pipeline {
    agent any

    options {
        disableConcurrentBuilds()
        timestamps()
    }

    parameters {
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Ejecuta tests Maven')
        booleanParam(name: 'BUILD_DOCKER', defaultValue: true, description: 'Construye imagen Docker si existe Dockerfile')
        booleanParam(name: 'PUSH_DOCKER', defaultValue: true, description: 'Publica la imagen Docker al registry configurado')
        string(name: 'DOCKER_REGISTRY', defaultValue: 'ghcr.io/japvidal', description: 'Registry Docker opcional, por ejemplo ghcr.io/japvidal')
        string(name: 'DOCKER_CREDENTIALS_ID', defaultValue: 'ghcr-japvidal', description: 'Credencial Jenkins para docker login')
    }

    environment {
        IMAGE_NAME = 'tikitakas/competitions'
        IMAGE_TAG = ''
        IMAGE_REF = ''
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Detect Project') {
            steps {
                script {
                    env.IMAGE_TAG = (env.BRANCH_NAME ?: "build-${env.BUILD_NUMBER}").replaceAll('[^A-Za-z0-9_.-]', '-')
                    env.IMAGE_REF = params.DOCKER_REGISTRY?.trim()
                        ? "${params.DOCKER_REGISTRY}/${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                        : "${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                }
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw -B clean install -DskipTests'
            }
        }

        stage('Test') {
            when {
                expression { return params.RUN_TESTS }
            }
            steps {
                sh './mvnw -B test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            when {
                expression { return params.BUILD_DOCKER }
            }
            steps {
                script {
                    def registry = params.DOCKER_REGISTRY?.trim()
                    if (!registry) {
                        registry = 'ghcr.io/japvidal'
                    }
                    def imageName = 'tikitakas/competitions'
                    def imageTag = (env.BRANCH_NAME ?: "build-${env.BUILD_NUMBER}").replaceAll('[^A-Za-z0-9_.-]', '-')
                    def imageRef = "${registry}/${imageName}:${imageTag}"
                    env.IMAGE_TAG = imageTag
                    env.IMAGE_REF = imageRef
                    echo "IMAGE_REF=${env.IMAGE_REF}"
                    sh "docker build -f Dockerfile -t ${env.IMAGE_REF} ."
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { return params.BUILD_DOCKER && params.PUSH_DOCKER }
            }
            steps {
                script {
                    def registry = params.DOCKER_REGISTRY?.trim()
                    if (!registry) {
                        registry = 'ghcr.io/japvidal'
                    }
                    def imageTag = (env.BRANCH_NAME ?: "build-${env.BUILD_NUMBER}").replaceAll('[^A-Za-z0-9_.-]', '-')
                    def imageRef = "${registry}/tikitakas/competitions:${imageTag}"
                    env.IMAGE_TAG = imageTag
                    env.IMAGE_REF = imageRef
                    echo "PUSH_IMAGE_REF=${imageRef}"
                    if (params.DOCKER_CREDENTIALS_ID?.trim()) {
                        withCredentials([
                            usernamePassword(
                                credentialsId: params.DOCKER_CREDENTIALS_ID,
                                usernameVariable: 'DOCKER_USERNAME',
                                passwordVariable: 'DOCKER_PASSWORD'
                            )
                        ]) {
                            sh """
                                set +x
                                if [ -n "${registry}" ]; then
                                  echo "\${DOCKER_PASSWORD}" | docker login "${registry}" -u "\${DOCKER_USERNAME}" --password-stdin
                                fi
                                docker push "${imageRef}"
                            """
                        }
                    } else {
                        sh "docker push ${imageRef}"
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true, fingerprint: true
        }
    }
}
