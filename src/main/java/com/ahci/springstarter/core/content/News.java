package com.ahci.springstarter.core.content;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class News {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(length=32)
    private String title;

	@Column(length=64)
    private String description;

    @Column(columnDefinition="text")
    private String content;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "category_news", 
      joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "category_id", 
      referencedColumnName = "id"))
    private List<Category> categories;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "news_tag", 
      joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "tag_id", 
      referencedColumnName = "id"))
    private List<Tag> tags;
    
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Tag> getTags() {	
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
    
    
}
