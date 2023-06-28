package com.rhonda.bookstoredemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity()
@Table(name= "BOOK_MODEL")
public class BookModel {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = {
                    @JoinColumn(name = "book_id", referencedColumnName = "book_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "author_id",
                            nullable = false, updatable = false)})
    private List<AuthorModel> authors;

    private int year;

    private double price;

    private String genre;
}
