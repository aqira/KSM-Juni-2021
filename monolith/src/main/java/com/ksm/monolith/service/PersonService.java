/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.monolith.service;

import com.ksm.monolith.model.Belonging;
import com.ksm.monolith.model.Person;
import com.ksm.monolith.repository.BelongingRepository;
import com.ksm.monolith.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author aqira
 */
@Service
public class PersonService {

    @Autowired //dependency injection 
    PersonRepository personRepository;
    @Autowired
    BelongingRepository belongingRepository;

    //Create
    public Person insert(Person person) {
        if (person.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        return personRepository.save(person);
    }

    //Read All
    public List<Person> getAllPerson() {
        List<Person> people = personRepository.findAll();
        return people;
    }

    public void dummyData() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Aqira", "Kelana"));
        people.add(new Person("Kevin", "Harlim"));
        people.add(new Person("Lois", "Ceka"));
        personRepository.saveAll(people);
    }

    //Read -> getById(Integer id) -> findById(id)
    public Person getById(Integer id) {
        if (personRepository.existsById(id)) {
            return personRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    //Update -> update(Integer id) -> 1 Kevin Harlim
    public Person update(Integer id, Person person) {
        Person oldPerson = getById(id);
        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());

        return personRepository.save(oldPerson);
    }

    //Delete -> deleteById(Integer id)
    public Person deleteById(Integer id) {
        Person person = getById(id);
        personRepository.delete(person);
        return person;
    }
    //  1

//    public void getAllPersonsBelongings(Integer id) {
//        // 1 Aqira Kelana Male
//        Person person = getById(id);
//
//        //          1       -> 1 , 2 , 3 , 4
//        for (Belonging belonging : person.getBelongings()) {
//            //pulpen, penghapus, pensil, penggaris
//            System.out.println(belonging.getName());
//            //Aqira, Aqira, Aqira, Aqira
//            System.out.println(belonging.getPerson().getFirstName());
//        }
//    }
//
//    public Person givePencilAndEraserToNewPerson(Integer id) {
//        Person person = getById(id);
//        List<Belonging> belongings = new ArrayList<>();
//        belongings.add(new Belonging("Pencil", 3, person)); //1
//        belongings.add(new Belonging("Eraser", 1, person)); //2
//        List<Belonging> insertedBelongings = belongingRepository.saveAll(belongings);
//        person.setBelongings(insertedBelongings);
//        personRepository.save(person);
//        return person;
//    }

}
