package com.springboot.swagger.demo.services;

import com.springboot.swagger.demo.domain.Todo;
import com.springboot.swagger.demo.repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional
    public List<Todo> list()  {
        List<Todo> todos = todoRepository.findAll();
        logger.info("Todos found :: "  + todos.size());
        return todos;
   }


}
