package com.hack.casesOrIfs.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "content")
@SqlResultSetMapping(
	    name = "ContentMapping",
	    entities = @EntityResult(entityClass = Content.class))	
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@Lob
	private String resumen;
	private String font;
	private String link;

	@Column(nullable = false, name = "future_job")
	private boolean futureJob;
	
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

}
