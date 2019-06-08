package com.ahci.springstarter.core.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ahci.springstarter.core.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
