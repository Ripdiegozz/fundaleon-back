package com.fundaleonREST.fundaleonapirest.repository;

import com.fundaleonREST.fundaleonapirest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findById(UUID id);
    Book findByIsbn(String isbn);
    void deleteById(UUID id);

    boolean existsByIsbn(String isbn);
}
