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

### Screenshots
```Step 1 :User signup with Postman```

![image](https://user-images.githubusercontent.com/17017086/49659707-6a9b9c00-fa0a-11e8-9159-827acff233f6.png)

```Step 2 :User Login with Postman.Copy the token generated on successful login```

![image](https://user-images.githubusercontent.com/17017086/49659720-712a1380-fa0a-11e8-8436-de4500014138.png)

```Step 3 :On Swagger UI ( http://localhost:8080/springboot-swagger-demo/swagger-ui.html ) click authorize and paste token from Step 2```

![image](https://user-images.githubusercontent.com/17017086/49659738-7f782f80-fa0a-11e8-8ca2-c8f2f8c92913.png)

```Step 4:Execute the API.Note the bearer token added in authorization header in generated curl```

![image](https://user-images.githubusercontent.com/17017086/49659730-78e9b800-fa0a-11e8-8086-540cf4c8655c.png)

