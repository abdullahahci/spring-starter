package com.ahci.springstarter.core.content;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.ahci.springstarter.admin.models.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class News {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=32)
	@NotEmpty(message = "{title.notempty}")
	private String title;

	@Column(length=64)
	@NotEmpty(message = "{spot.notempty}")
	private String spot;

	@Column(columnDefinition="text")
	private String content;

	private String image;

	private String source;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "category_news", 
	  joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"), 
	  inverseJoinColumns = @JoinColumn(name = "category_id", 
	  referencedColumnName = "id"))
	@JsonManagedReference
	private List<Category> categories;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "news_tag", 
	  joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"), 
	  inverseJoinColumns = @JoinColumn(name = "tag_id", 
	  referencedColumnName = "id"))
	private List<Tag> tags;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
	
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
	
	public News() {
		this.createdAt = this.updatedAt = Calendar.getInstance().getTime();
	}
	
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

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void addCategory(Category category) {
		if (categories ==null)
			categories = new ArrayList<Category>();
		if(!categories.contains(category))
			categories.add(category);
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addTag(Tag tag) {
		if (tags ==null)
			tags = new ArrayList<Tag>();
		if(!tags.contains(tag))
			tags.add(tag);
	}
	
	public List<Tag> getTags() {	
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
