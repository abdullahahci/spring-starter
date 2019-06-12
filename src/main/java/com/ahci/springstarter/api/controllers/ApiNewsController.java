package com.ahci.springstarter.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahci.springstarter.api.repositories.NewsRepository;
import com.ahci.springstarter.core.content.Category;
import com.ahci.springstarter.core.content.News;
import com.ahci.springstarter.core.repositories.CategoryRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/news") // This means URL's start with /demo (after Application path)
public class ApiNewsController {
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private NewsRepository newsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@GetMapping(path="/{id}")
	public @ResponseBody Optional<News> getNewsWithId(@PathVariable @NotNull Integer id) {
		// This returns a JSON or XML with the users
		return newsRepository.findById(id);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<News> getAllNews() {
		// This returns a JSON or XML with the users
		return newsRepository.findAll();
	}
	
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody List<News> findCategoryNews(@PathVariable @NotNull Integer categoryId) {
		return categoryRepository.findById(categoryId).get().getCategoryNews();
	}
}