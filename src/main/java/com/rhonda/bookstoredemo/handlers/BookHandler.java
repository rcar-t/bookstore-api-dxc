package com.rhonda.bookstoredemo.handlers;

import com.rhonda.bookstoredemo.dto.BookDTO;
import com.rhonda.bookstoredemo.services.BookService;
import com.rhonda.bookstoredemo.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Component
public class BookHandler {

    private final Logger logger = LogManager.getLogger(BookHandler.class);

    private final Util util = new Util();

    @Autowired
    private BookService bookService;

    public Mono<ServerResponse> insertBook(ServerRequest serverRequest){
        logger.trace(
                util.format("Insert book called at {}", Instant.ofEpochMilli(System.currentTimeMillis()).toString())
        );
        return serverRequest
                .bodyToMono(BookDTO.class)
                .flatMap(it -> ServerResponse
                        .created(URI.create("/api/v1/books/create"))
                        .body(bookService.insertOrUpdateBook(it), BookDTO.class)
                );
    }

    public Mono<ServerResponse> getBook(ServerRequest serverRequest){
        logger.trace(
                util.format("Get book called at {}", Instant.ofEpochMilli(System.currentTimeMillis()).toString())
        );
        Optional<String> title = serverRequest.queryParam("title");
        Optional<String> authorNames = serverRequest.queryParam("authors");
        return bookService.getBookByTitleOrAuthorName(title, authorNames).collectList()
                .flatMap(it -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(it, List.class));
    }

    public Mono<ServerResponse> updateBook(ServerRequest serverRequest){
        return serverRequest
                .bodyToMono(BookDTO.class)
                .flatMap(it -> ServerResponse.ok()
                        .body(bookService.insertOrUpdateBook(it), BookDTO.class)
                );
    }

    public Mono<ServerResponse> deleteBook(ServerRequest serverRequest){
        Optional<String> id = serverRequest.queryParam("book_id");
        if (id.isPresent()) {
            if (bookService.deleteBook(id.get())) {
                String message = util.format("Book {} deleted successfully.", id.get());
                return ServerResponse.ok().body(Mono.just(message), String.class);
            }
        }
        return ServerResponse.notFound().build();
    }
}
