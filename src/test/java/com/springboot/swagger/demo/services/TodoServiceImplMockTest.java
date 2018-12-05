package com.springboot.swagger.demo.services;

import com.springboot.swagger.demo.domain.Todo;
import com.springboot.swagger.demo.repositories.TodoRepository;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class TodoServiceImplMockTest {

    private TodoServiceImpl todoServiceImpl;
    @Mock
    private TodoRepository todoRepository;
    @Mock
    private Todo todo;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        todoServiceImpl=new TodoServiceImpl();
        todoServiceImpl.setTodoRepository(todoRepository);
    }


}