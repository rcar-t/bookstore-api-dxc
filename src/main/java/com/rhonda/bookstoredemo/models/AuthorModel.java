package com.rhonda.bookstoredemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table()
public class AuthorModel {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<BookModel> books;
}

