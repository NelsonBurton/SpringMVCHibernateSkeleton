package com.controller;

import com.dao.PersonDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Person;

import java.util.List;

@Controller
public class PersonController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PersonDAO personDAO;
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personDAO.listPersons());
        return "person";
    }

    @RequestMapping(value = "/dogs", // consumes = "application/json",produces = "application/json",
                     method = RequestMethod.GET)
    public String listPersonsJSON() throws JsonProcessingException{
        List<Person> personList = this.personDAO.listPersons();
        return mapper.writeValueAsString(personList);
    }
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.personDAO.addPerson(p);
		}else{
			//existing person, call update
			this.personDAO.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personDAO.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personDAO.getPersonById(id));
        model.addAttribute("listPersons", this.personDAO.listPersons());
        return "person";
    }
	
}
