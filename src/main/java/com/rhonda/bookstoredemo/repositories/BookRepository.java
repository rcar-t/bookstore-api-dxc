package com.rhonda.bookstoredemo.repositories;

import com.rhonda.bookstoredemo.models.BookModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<BookModel, String> {

    BookModel findByTitle(String title);

    @Query("SELECT b FROM BookModel b " +
            "JOIN b.authors a " +
            "WHERE a.id IN :authorIds " +
            "AND b.title = :title")
    List<BookModel> findByTitleAndAuthor(
            @Param("authorIds") List<String> authorIds,
            @Param("title") String title
    );

    @Query("SELECT b FROM BookModel b " +
            "JOIN b.authors a " +
            "WHERE a.id IN :authorIds")
    List<BookModel> findByAuthors(@Param("authorIds") List<String> authorIds);
}

