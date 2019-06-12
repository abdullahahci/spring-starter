package com.ahci.springstarter.admin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ahci.springstarter.core.content.News;

public interface AdminNewsRepository extends CrudRepository<News, Integer> {

}
