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
Step 1 :Signup a user: 
![image](https://user-images.githubusercontent.com/17017086/49659150-26f46280-fa09-11e8-8d4d-b1da5577c83a.png)

Step2 :Login with user created in Step1.Copy the access token in response.
![image](https://user-images.githubusercontent.com/17017086/49659023-ef85b600-fa08-11e8-8596-af788c0293cb.png)

Step3 :Navigate to http://localhost:8080/swagger-ui.html and select authorize button and enter access token as below

Step 4:Now execute API using swagger.

![image](https://user-images.githubusercontent.com/17017086/49542869-4ae66580-f89c-11e8-9c44-cb0e48f2d033.png)
