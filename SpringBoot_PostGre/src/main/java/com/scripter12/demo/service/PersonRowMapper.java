package com.scripter12.demo.service;

import com.scripter12.demo.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PersonRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person(UUID.fromString(resultSet.getString("personId")),resultSet.getString("personName"));
        return person;
    }
}
