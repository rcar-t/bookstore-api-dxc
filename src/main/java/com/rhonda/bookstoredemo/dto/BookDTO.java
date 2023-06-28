package com.rhonda.bookstoredemo.dto;

import com.rhonda.bookstoredemo.models.BookModel;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BookDTO {

    private Long id;

    @NotBlank(message = "isbn cannot be empty")
    private String isbn;

    @NotBlank(message = "title cannot be empty")
    @Size(min = 2, max = 250, message = "title length must be between {min} and {max}")
    private String title;

    @NotNull(message = "authors cannot be null")
    @NotEmpty(message = "must have at least one author")
    private List<AuthorDTO> authors;

    @NotNull(message = "year cannot be null")
    @Positive(message = "year must be greater than 0")
    @Digits(integer = 4, fraction = 0)
    private int year;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be greater than 0")
    private double price;

    @NotBlank(message = "genre cannot be empty")
    private String genre;

    public BookModel toBookModel() {
        return new BookModel()
                .setId(id)
                .setIsbn(isbn)
                .setTitle(title)
                .setAuthors(authors.stream().map(AuthorDTO::toAuthorModel).toList())
                .setYear(year)
                .setPrice(price)
                .setGenre(genre);
    }

    public BookDTO(BookModel bookModel) {
        id = bookModel.getId();
        isbn = bookModel.getIsbn();
        title = bookModel.getTitle();
        authors = bookModel.getAuthors().stream().map(AuthorDTO::new).toList();
        year = bookModel.getYear();
        price = bookModel.getPrice();
        genre = bookModel.getGenre();
    }
}
