package com.springboot.swagger.demo.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.*;


/**
 * This class is used to document login operation in swagger
 */
public class LoginOperations extends ApiListingScanner {

    @Autowired
    private TypeResolver typeResolver;

    @Autowired
    public LoginOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager){
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context)
    {
        final Multimap<String, ApiListing> def = super.scan(context);
        final List<ApiDescription> apis = new LinkedList<>();
        final List<Operation> operations = new ArrayList<>();

        operations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .tags(Sets.newHashSet("login"))
                .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE.toString()))
                .produces(Sets.newHashSet(MediaType.TEXT_PLAIN.toString()))
                .uniqueId("login")
                .parameters(Arrays.asList(new ParameterBuilder()
                        .name("body")
                        .required(true)
                        .description("Example POST Request JSON : {\\\"username\\\":\\\"username\\\",\\\"password\\\":\\\"password\\\"}")
                        .parameterType("body")
                        .type(typeResolver.resolve(String.class))
                        .modelRef(new ModelRef("string"))
                        //Github - open issue https://github.com/springfox/springfox/issues/2561 so commenting below.
                        //.type(typeResolver.resolve(ApplicationUser.class))
                        //.modelRef(new ModelRef(ApplicationUser.class.getSimpleName()))
                        .build()))
                .summary("Login here")
                .responseMessages(responseMessages())
                //.notes("Login here.")
                .build());

        apis.add(new ApiDescription("login","/login", "Authentication documentation", operations, false));

        def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
                .apis(apis)
                .description("Custom authentication")
                .build());


        return def;
    }

    /**
     * @return Set of response messages that overide
     * the default/global response messages
     */
    private Set<ResponseMessage> responseMessages() {

        //https://stackoverflow.com/questions/44075229/swagger-ui-does-not-show-the-response-body
        //Swagger UI doesnt display response body just the header.
        Set<ResponseMessage> messages = new HashSet<>();
        messages.add(new ResponseMessageBuilder()
                .code(HttpStatus.OK.value())
                .message("Login successful.Auth token generated in response header.")
                .build());

        messages.add(new ResponseMessageBuilder()
                .code(HttpStatus.FORBIDDEN.value())
                .message("Login failed.Credentials not found.")
                .build());


        messages.add(new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Invalid request.Valid request is {\"username\":\"username\",\"password\":\"password\"}")
                .build());

        messages.add(new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal Server Error.")
                .build());


        return messages;
    }

}
