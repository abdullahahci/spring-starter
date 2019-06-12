package com.ahci.springstarter.core.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(length=32)
    private String title;
	
	@Column(length=255)
    private String image;
	
	@Column(length=255)
    private String content;
	
	private int order;

	@ManyToOne
	@JoinColumn(name="gallery_id", nullable=false)
	private PhotoGallery gallery;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
