package com.rhonda.bookstoredemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "BOOKS")
public class BookModel {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_Sequence")
    @SequenceGenerator(name="id_Sqeuence", sequenceName="ID_SEQ")
    private String id;

    private String isbn;

    private String title;

    private ArrayList<String> authors;

    private int year;

    private double price;

    private String genre;
}
