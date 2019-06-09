package com.ahci.springstarter.admin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ahci.springstarter.admin.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
