package com.fundaleonREST.fundaleonapirest.repository;

import com.fundaleonREST.fundaleonapirest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findById(UUID id);
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    void deleteById(UUID id);
    List<Book> findAll();
    List<Book> findBooksByAuthor(String author);
    boolean existsByIsbn(String isbn);
}
