package com.rhonda.bookstoredemo.services;

import com.rhonda.bookstoredemo.dto.BookDTO;
import com.rhonda.bookstoredemo.models.AuthorModel;
import com.rhonda.bookstoredemo.repositories.AuthorRepository;
import com.rhonda.bookstoredemo.repositories.BookRepository;
import com.rhonda.bookstoredemo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final Util util = new Util();

    @Override
    public Mono<BookDTO> insertOrUpdateBook(BookDTO bookDTO) {
        return Mono.just(
                new BookDTO(bookRepository.save(bookDTO.toBookModel()))
        );
    }

    @Override
    public Flux<BookDTO> getBookByTitleOrAuthorName(Optional<String> title, Optional<String> authorNames) {
        List<BookDTO> books = List.of();

        if (util.isValid(title) && util.isValid(authorNames)) {
            books = bookRepository.findByTitleAndAuthor(getAuthorIds(authorNames.get()), title.get())
                    .stream().map(BookDTO::new).toList();
        } else if (util.isValid(title) && !util.isValid(authorNames)) {
            books = List.of(new BookDTO(bookRepository.findByTitle(title.get())));
        } else if (!util.isValid(title) && util.isValid(authorNames)){
            books = bookRepository.findByAuthors(getAuthorIds(authorNames.get()))
                    .stream().map(BookDTO::new).toList();
        }

        return Flux.fromIterable(books);
    }

    private List<String> getAuthorIds(String authorNames) {
        return Arrays.stream(authorNames.split(","))
                .map(it -> authorRepository.findByName(it).getId()).toList();
    }
}
