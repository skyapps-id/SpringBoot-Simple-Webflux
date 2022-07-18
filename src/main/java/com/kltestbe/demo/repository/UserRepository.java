package com.kltestbe.demo.repository;

import org.springframework.stereotype.Repository;

import com.kltestbe.demo.model.User;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
}
