package com.ahci.springstarter.core.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ahci.springstarter.core.content.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
