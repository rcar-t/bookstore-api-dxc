package com.rhonda.bookstoredemo.repositories;

import com.rhonda.bookstoredemo.models.BookModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

import java.util.List;


public interface BookRepository extends CrudRepository<BookModel, String> {

    BookModel findByTitle(String title);

    @Query("SELECT * FROM BOOKS" +
            "  INNER JOIN books_authors ON BOOKS.book_id = books_authors.book_id" +
            "WHERE books_authors.author_id IN ?1" +
            "  AND BOOKS.title = ?2")
    List<BookModel> findByTitleAndAuthor(String authorIds, String title);

    @Query("SELECT * FROM BOOKS " +
            "  INNER JOIN books_authors ON BOOKS.book_id = books_authors.book_id " +
            "WHERE books_authors.author_id IN ?1")
    List<BookModel> findByAuthors(String authorIds);
}

