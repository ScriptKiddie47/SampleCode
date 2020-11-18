package com.scripter12.demo.dao;

import com.scripter12.demo.model.Person;
import com.scripter12.demo.service.PersonRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postGres")
public class PersonDaoImpl implements PersonDao {
    NamedParameterJdbcTemplate template;
    public PersonDaoImpl(NamedParameterJdbcTemplate template){
        this.template = template;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        //return List.of(new Person(UUID.randomUUID(),"FROM POSTGRES DB"));
        return template.query("select * from personData",new PersonRowMapper());
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }
}
