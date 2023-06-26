package com.rhonda.bookstoredemo.repositories;

import com.rhonda.bookstoredemo.models.AuthorModel;
import com.rhonda.bookstoredemo.models.BookModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorModel, String> {

    AuthorModel findByName(String title);

}
