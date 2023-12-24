package org.example.gb.lesson4.hw;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
        @NamedQuery(name = "Author.findAuthorByName",
                query = "SELECT a FROM Author a WHERE a.name = :name")})
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Author(String name) {
        this.name = name;
    }

//    @OneToMany(mappedBy = "author")
//    private List<Book> books;

//    public void addBook(Book book) {
//        if (books == null) {
//            books = new ArrayList<>();
//        }
//
//        books.add(book);
//        book.setAuthor(this);
//    }
}
