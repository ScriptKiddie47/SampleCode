package com.scripter12.demo.service;

import com.scripter12.demo.dao.PersonDao;
import com.scripter12.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("PService")
public class PersonService {

    private final PersonDao personDao;

    /*@Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }*/
    @Autowired
    public PersonService(@Qualifier("postGres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        //System.out.println("Name:"+person.getName());
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id,Person person){
        return personDao.updatePersonById(id,person);
    }
}
