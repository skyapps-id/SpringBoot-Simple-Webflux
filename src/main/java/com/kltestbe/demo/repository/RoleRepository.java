package com.kltestbe.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kltestbe.demo.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
