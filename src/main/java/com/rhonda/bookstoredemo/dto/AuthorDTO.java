package com.rhonda.bookstoredemo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AuthorDTO {

    private String id;

    @NotBlank(message = "isbn cannot be empty")
    private String name;

    private Date birthday;
}
