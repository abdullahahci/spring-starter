package com.ahci.springstarter.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ahci.springstarter.core.content.News;

public interface NewsRepository extends CrudRepository<News, Integer> {

}
