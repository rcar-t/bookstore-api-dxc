package com.rhonda.bookstoredemo.repositories;

import com.rhonda.bookstoredemo.models.BookModel;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookModel, String> {
}
