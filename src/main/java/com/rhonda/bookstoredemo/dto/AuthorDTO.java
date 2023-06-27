package com.rhonda.bookstoredemo.dto;

import com.rhonda.bookstoredemo.models.AuthorModel;
import com.rhonda.bookstoredemo.models.BookModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AuthorDTO {

    private String id;

    @NotBlank(message = "isbn cannot be empty")
    private String name;

    private Date birthday;

    public AuthorModel toAuthorModel() {
        return new AuthorModel()
                .setId(id)
                .setName(name)
                .setBirthday(birthday);
    }

    public AuthorDTO(AuthorModel authorModel) {
        id = authorModel.getId();
        name = authorModel.getName();
        birthday = authorModel.getBirthday();
    }
}
