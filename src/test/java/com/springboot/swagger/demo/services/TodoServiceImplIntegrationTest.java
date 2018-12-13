package com.springboot.swagger.demo.services;

import com.springboot.swagger.demo.domain.Todo;
import com.springboot.swagger.demo.repositories.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TodoServiceImplIntegrationTest {

    private TodoServiceImpl todoService;
    private TodoRepository mockTodoRepository;

    @Before
    public void setUp() {
        mockTodoRepository = Mockito.mock(TodoRepository.class);
        todoService = new TodoServiceImpl();
        todoService.setTodoRepository(mockTodoRepository);
    }


    @Test
    public void testListTodos() {
        Todo todo = new Todo();
        todo.setDescription("TEST");
        when(mockTodoRepository.findAll()).thenReturn(Collections.singletonList(todo));
        List<Todo> todos = todoService.list();
        assertEquals(todos.size(),1);
    }

}
