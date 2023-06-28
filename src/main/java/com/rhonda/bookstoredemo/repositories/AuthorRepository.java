package com.rhonda.bookstoredemo.repositories;

import com.rhonda.bookstoredemo.models.AuthorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorModel, Long> {

    AuthorModel findByName(String title);

}
