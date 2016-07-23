package com.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = { "classpath:/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDAOTest {
    @Autowired
    ApplicationContext context;

    @Autowired
    private PersonDAO personDao;

    @Test
    public void testListPersons() {
        System.out.println("*************************");
        System.out.println(personDao.getPersonById(8).getCountry());
        System.out.println("*************************");
    }
}