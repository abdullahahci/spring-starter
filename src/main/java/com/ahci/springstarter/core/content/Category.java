package com.ahci.springstarter.core.content;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<News> categoryNews;
    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<News> getCategoryNews() {
		return categoryNews;
	}

	public void setCategoryNews(List<News> categoryNews) {
		this.categoryNews = categoryNews;
	}
}
