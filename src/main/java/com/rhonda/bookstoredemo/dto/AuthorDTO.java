package com.rhonda.bookstoredemo.dto;

import com.rhonda.bookstoredemo.models.AuthorModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AuthorDTO {

    private Long id;

    @NotBlank(message = "isbn cannot be empty")
    private String name;

    @NotBlank(message = "birthday cannot be empty")
    private String birthday;

    public AuthorModel toAuthorModel() {
        return new AuthorModel()
                .setId(id)
                .setName(name)
                .setBirthday(birthdayInDate());
    }

    public AuthorDTO(AuthorModel authorModel) {
        id = authorModel.getId();
        name = authorModel.getName();
        birthday = authorModel.getBirthday().toString();
    }

    public LocalDate birthdayInDate() {
        return LocalDate.parse(birthday);
    }
}
