package com.microservicios.app.futfem.competitions.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competitions")
public class Competition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String association;
	private String country;
	private String urlpic;
	@Column(name = "established")
	private Date established;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getEstablished() {
		return established;
	}

	public void setEstablished(Date established) {
		this.established = established;
	}

	public String getUrlpic() {
		return urlpic;
	}

	public void setUrlpic(String urlpic) {
		this.urlpic = urlpic;
	}

}
