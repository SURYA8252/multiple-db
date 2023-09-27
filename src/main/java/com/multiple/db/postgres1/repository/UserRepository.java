package com.multiple.db.postgres1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.db.postgres1.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
