package org.example.gb.lesson4.hw;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "Book.findBookByAuthor",
                query = "SELECT b FROM Book b WHERE b.author.id = :author")})
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public Book(String name) {
        this.name = name;
    }
}
