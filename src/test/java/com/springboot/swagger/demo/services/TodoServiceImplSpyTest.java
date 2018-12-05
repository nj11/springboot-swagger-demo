package com.springboot.swagger.demo.services;

import com.springboot.swagger.demo.domain.Todo;
import com.springboot.swagger.demo.repositories.TodoRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplSpyTest {
    @Spy
    private TodoServiceImpl todoServiceSpy;
    @Mock
    private TodoRepository todoRepository;
    @Mock
    private Todo todo;

}