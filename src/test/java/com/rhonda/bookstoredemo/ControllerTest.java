package com.rhonda.bookstoredemo;

import com.rhonda.bookstoredemo.dto.AuthorDTO;
import com.rhonda.bookstoredemo.dto.BookDTO;
import com.rhonda.bookstoredemo.handlers.BookHandler;
import com.rhonda.bookstoredemo.handlers.GlobalErrorHandler;
import com.rhonda.bookstoredemo.routers.ApiRoutes;
import com.rhonda.bookstoredemo.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = { ApiRoutes.class, BookHandler.class, GlobalErrorHandler.class })
@WebFluxTest()
public class ControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private ApplicationContext context;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void insertBookOkTest() {
        AuthorDTO author = new AuthorDTO(1L, "name", new Date(12000L));
        BookDTO book = new BookDTO(1L, "isbn", "title", List.of(author), 1900, 50.4, "genre");

        when(bookService.insertOrUpdateBook(any())).thenReturn(Mono.just(book));

        webTestClient.post().uri("/api/v1/book/create")
                .body(Mono.just(book), BookDTO.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L);
    }

    @Test
    public void insertBookDataValidationTest() {
        AuthorDTO author = new AuthorDTO(1L, "name", new Date(12000L));
        BookDTO book = new BookDTO(1L, "", "", List.of(author), -40, 50.4, "genre");

        when(bookService.insertOrUpdateBook(any())).thenReturn(Mono.just(book));

        webTestClient.post().uri("/api/v1/book/create")
                .body(Mono.just(book), BookDTO.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$").value(containsString("title cannot be empty"))
                .jsonPath("$").value(containsString("title length must be between 2 and 250"))
                .jsonPath("$").value(containsString("year must be greater than 0"))
                .jsonPath("$").value(containsString("isbn cannot be empty"));
    }

}
