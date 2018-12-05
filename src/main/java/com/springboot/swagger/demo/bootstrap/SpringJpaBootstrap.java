package com.springboot.swagger.demo.bootstrap;

import com.springboot.swagger.demo.domain.Todo;
import com.springboot.swagger.demo.repositories.TodoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private TodoRepository todoRepository;
    private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Todo todo1 = new Todo();
        todo1.setCompleted(false);
        todo1.setDescription("Description for Todo 1");
        todo1.setExpectedBeginDate(new Date());
        todoRepository.save(todo1);
    }

 }



