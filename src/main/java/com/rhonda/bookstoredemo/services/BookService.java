package com.rhonda.bookstoredemo.services;

import com.rhonda.bookstoredemo.dto.BookDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface BookService {

    Mono<BookDTO> insertOrUpdateBook(BookDTO bookDTO);

    Flux<BookDTO> getBookByTitleOrAuthorName(Optional<String> title, Optional<String> authorNames);
}
