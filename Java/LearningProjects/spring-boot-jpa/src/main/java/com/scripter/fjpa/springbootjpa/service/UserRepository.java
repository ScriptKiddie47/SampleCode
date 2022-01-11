package com.scripter.fjpa.springbootjpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scripter.fjpa.springbootjpa.entity.User;

//JpaRepository<Entity we want to manage, primary key [ Long Wrapper ] >
public interface UserRepository extends JpaRepository<User, Long> {
}
