package com.hack.casesOrIfs.entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "content")
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String resumen;
	private String font;
	private String link;
	private boolean futureJob;
	private Date favoriteDate;
	
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
	
	public String getResumen() {
		return resumen;
	}
	
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public String getFont() {
		return font;
	}
	
	public void setFont(String font) {
		this.font = font;
	}
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
		
	public boolean isFutureJob() {
		return futureJob;
	}
	
	public void setFutureJob(boolean futureJob) {
		this.futureJob = futureJob;
	}
	
	public Date getFavoriteDate() {
		return favoriteDate;
	}
	
	public void setFavoriteDate(Date favoriteDate) {
		this.favoriteDate = favoriteDate;
	}

}
