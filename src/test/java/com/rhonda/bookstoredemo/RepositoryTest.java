package com.rhonda.bookstoredemo;

import com.rhonda.bookstoredemo.models.AuthorModel;
import com.rhonda.bookstoredemo.models.BookModel;
import com.rhonda.bookstoredemo.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void testAddBook() {
        AuthorModel author = new AuthorModel().setName("name").setBirthday(LocalDate.parse("2019-10-12"));
        BookModel book = new BookModel().setTitle("title").setIsbn("isbn").setGenre("genre").setPrice(40).setAuthors(List.of(author)).setYearPublished(2020);

        repository.save(book);
        BookModel result = repository.findByTitle("title");
        Assertions.assertEquals(result.getYearPublished(), 2020);
    }

    @Test
    public void testFindByTitleAndAuthor() {
        AuthorModel author = new AuthorModel().setName("name").setBirthday(LocalDate.parse("2019-10-12"));
        BookModel book = new BookModel().setTitle("title").setIsbn("isbn").setGenre("genre").setPrice(40).setAuthors(List.of(author)).setYearPublished(2020);

        repository.save(book);
        BookModel result = repository.findByTitle("title");
        Assertions.assertEquals(result.getYearPublished(), 2020);
    }
}
