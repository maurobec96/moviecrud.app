package com.prueba.moviecrud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class Landing {

    String landingPage = """
        <!DOCTYPE html>
        <html>
        <body>
        <h1>Moviecrud</h1>

        <h2>Simple Movie application</h2>
        
        <p><a href="http://localhost:8080/swagger-ui/index.html#/">API Documentation</a></p>

        </body>
        </html>
            """;
    
    @RequestMapping
    public String hello(){
        return landingPage;
    }
    
}
