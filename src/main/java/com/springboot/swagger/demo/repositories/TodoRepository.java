package com.springboot.swagger.demo.repositories;

import com.springboot.swagger.demo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t where t.isCompleted = true")
    public List<Todo> listAllCompletedTodos();

    @Query("select t from Todo t where t.isCompleted = false")
    public List<Todo> listAllIncompleteTodos();


}
