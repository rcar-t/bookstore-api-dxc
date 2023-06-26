package com.rhonda.bookstoredemo.routers;

import com.rhonda.bookstoredemo.handlers.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ApiRoutes {

    @Bean
    public RouterFunction<ServerResponse> apiRouter(BookHandler bookHandler) {
       return route().path("/api/v1", apiBuilder -> apiBuilder
                .path("/book", bookBuilder -> bookBuilder
                        .POST("/create", accept(APPLICATION_JSON), bookHandler::insertBook)
                        .GET("/get", accept(APPLICATION_JSON), bookHandler::getBook)
                        .PUT("/update/{id}", accept(APPLICATION_JSON), bookHandler::updateBook)
                        .DELETE("/delete/{id}",  bookHandler::deleteBook)
                )
//               .path("/user", bookBuilder -> bookBuilder
//                       .GET()
//               )
        ).build();
    }
}
