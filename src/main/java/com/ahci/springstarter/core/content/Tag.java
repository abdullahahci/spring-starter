package com.ahci.springstarter.core.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ahci.springstarter.core.util.Util;

@Entity
public class Tag {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@Column(length=32)
	private String tag;
	
	@Column(length=32)
	private String slug;

	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(String tag) {
		super();
		this.tag = tag;
		this.slug = Util.toSlug(tag);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
}
