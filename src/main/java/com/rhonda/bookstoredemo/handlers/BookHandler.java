package com.rhonda.bookstoredemo.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {

    public Mono<ServerResponse> insertBook(ServerRequest serverRequest){
        return ServerResponse.ok().body(Mono.just("INSERT"), String.class);
    }

    public Mono<ServerResponse> updateBook(ServerRequest serverRequest){
        return ServerResponse.ok().body(Mono.just("UPDATE"), String.class);
    }

    public Mono<ServerResponse> deleteBook(ServerRequest serverRequest){
        return ServerResponse.ok().body(Mono.just("DELETE"), String.class);
    }
}
