package com.springboot.swagger.demo.repositories;

import com.springboot.swagger.demo.domain.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
/**
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
 * Whenever we are using any Spring Boot testing features in out JUnit tests, this annotation will be required.
 */
@DataJpaTest
/** @DataJpaTest - provides some standard setup for configuring persistence layer.
 *  configuring H2, an in-memory database
    setting Hibernate, Spring Data, and the DataSource
    performing an @EntityScan
    turning on SQL logging
 */
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    /**
     *  The TestEntityManager provided by Spring Boot is an alternative
     *  to the standard JPA EntityManager that provides methods
     *  commonly used when writing tests.
     */
    private TestEntityManager entityManager;


    @Test
    public void testSaveTodo() {
        //setup
        Todo todo = new Todo();
        todo.setDescription("Test todo");
        todoRepository.save(todo);
        assertNotNull(todo.getId()); //not null after save
        //fetch from DB
        Todo fetchedTodo = todoRepository.findById(todo.getId()).orElse(null);
        //should not be null
        assertNotNull(fetchedTodo);
        //should equal
        assertEquals(todo.getId(), fetchedTodo.getId());
        assertEquals(todo.getDescription(), fetchedTodo.getDescription());
        //update description and save
        fetchedTodo.setDescription("New Description");
        todoRepository.save(fetchedTodo);
        //get from DB, should be updated
        Todo fetchUpdatedTodo = todoRepository.findById(fetchedTodo.getId()).orElse(null);
        assertEquals(fetchedTodo.getDescription(), fetchUpdatedTodo.getDescription());
    }


}
