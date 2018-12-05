# Demo Application using REST,Spring Boot,JPA and Swagger.

### Setup Spring Boot Rest App with JAX-RS

Checkout and setup application using maven to generate war and deploy to web server.

```mvn clean compile package```

Navigate to this page to view generated rest swagger documentation

```http(s)://<servername>:<port>/springboot-swagger-demo/swagger-ui.html```
 
 Swagger config resides here.
 
```com.springboot.swagger.demo.config.SwaggerConfig```

API to query  :

URL : ```http://localhost:8080/springboot-swagger-demo/v1/todo/list```

METHOD : ```GET```

Sample Response (JSON)
```
[
  {
    "id": 0,
    "description": "Description for Todo 1",
    "expectedBeginDate": "2018-12-05",
    "expectedCompletionDate": null,
    "completed": false
  }
]

```

![image](https://user-images.githubusercontent.com/17017086/49542869-4ae66580-f89c-11e8-9c44-cb0e48f2d033.png)
