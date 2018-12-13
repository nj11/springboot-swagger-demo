package com.springboot.swagger.demo.controllers;

import com.springboot.swagger.demo.domain.Todo;
import com.springboot.swagger.demo.services.TodoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/")
@Api(value="Sample REST operations on todo objects")

public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TodoService todoService;

    @Autowired
    public void setService(@Qualifier("todoServiceImpl") TodoService todoService) {
        this.todoService = todoService;
    }

    @ApiOperation(value = "Search all todos"    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully completed request"),
            @ApiResponse(code = 400, message = "Bad client request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(path = "/todo/list",method = RequestMethod.GET)
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity query() {
        List<Todo> todos = todoService.list();
        return ResponseEntity.ok().body(todos);
    }




}
