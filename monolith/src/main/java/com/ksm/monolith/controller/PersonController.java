/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.monolith.controller;

import com.ksm.monolith.model.Person;
import com.ksm.monolith.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aqira
 */
@RestController //Restful API
@RequestMapping("api/person") // localhost:8088/api/person
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * - GET	-> getAll() 
     * - GET	-> getById() 
     * - POST	-> save(Person person) 
     * - PUT    -> saveNewLastName(Integer id, String lastName) 
     * - DELETE	-> delete(Integer id)
     */
    
    @GetMapping() //localhost:8088/api/person
    public List<Person> getAll(){
        return personService.getAllPerson();
    }
    
    @GetMapping("/{id}") //localhost:8088/api/person/1
    public Person getById(@PathVariable Integer id){
        return personService.getById(id);
    }
    
    @PostMapping() //localhost:8088/api/person
    public Person save(@RequestBody Person person){
        return personService.insert(person);
    }
    
    @PatchMapping("/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public Person save(@PathVariable Integer id, @RequestBody Person person){
        return personService.update(id, person);
    }
    
    @DeleteMapping("/{id}")
    public Person delete(@PathVariable Integer id){
        return personService.deleteById(id);
    }
}
