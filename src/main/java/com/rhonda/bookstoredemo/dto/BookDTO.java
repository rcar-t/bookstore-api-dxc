package com.rhonda.bookstoredemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class BookDTO {

    private String id;

    private String isbn;

    private String title;

    private ArrayList<String> authors;

    private int year;

    private double price;

    private String genre;
}
