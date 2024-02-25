package com.fundaleonREST.fundaleonapirest.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Entity(name = "book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private Long quantity;
    private Date publication_year;
    private String publisher;
    private Date created_at = new Date();
    private Date updated_at = null;
    private Boolean status = true;

    public Book() {
    }

    public Book(String title, String author, String genre, String isbn, Long quantity, Date publication_year,
                String publisher, Boolean status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.quantity = quantity;
        this.publication_year = publication_year;
        this.publisher = publisher;
        this.status = status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setPublication_year(Date publication_year) {
        this.publication_year = publication_year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isbn='" + isbn + '\'' +
                ", quantity=" + quantity +
                ", publication_year=" + publication_year +
                ", publisher='" + publisher + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", status=" + status +
                '}';
    }
}
