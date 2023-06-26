package com.rhonda.bookstoredemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "AUTHORS")
public class AuthorModel {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_Sequence")
    @SequenceGenerator(name="id_Sqeuence", sequenceName="ID_SEQ")
    private String id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<BookModel> books;
}
