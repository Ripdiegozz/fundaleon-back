package com.fundaleonREST.fundaleonapirest.service;

import com.fundaleonREST.fundaleonapirest.model.Book;
import com.fundaleonREST.fundaleonapirest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        System.out.println("Guardando libro en la base de datos" + book.toString());
        // Si no existe, guardar el usuario
        return bookRepository.save(book);
    }

    public Book editBook(Book book) {
        // Verificar si el usuario existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (optionalBook.isEmpty()) {
            throw new RuntimeException("El libro no existe en la base de datos con ese ID");
        }

        // Recopilar datos del libro
        Book bookToEdit = optionalBook.get();
        bookToEdit.setTitle(book.getTitle());
        bookToEdit.setAuthor(book.getAuthor());
        bookToEdit.setGenre(book.getGenre());
        bookToEdit.setIsbn(book.getIsbn());
        bookToEdit.setQuantity(book.getQuantity());
        bookToEdit.setPublication_year(book.getPublication_year());
        bookToEdit.setPublisher(book.getPublisher());
        bookToEdit.setUpdated_at(new Date());


        // Guardar el libro
        return bookRepository.save(bookToEdit);
    }

    public Book deleteBookById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findById(id);
        System.out.println(id);
        // Check if optionalBook is actually empty
        System.out.println(optionalBook.isEmpty());

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("El Libro no existe en la base de datos con ese ID");
        }

        // Recopilar datos del usuario
        Book book = optionalBook.get();

        // Eliminar el usuario
        bookRepository.deleteById(id);

        // Devolver los datos del usuario eliminado
        return book;
    }

    public Book getBookById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Devolver los datos del usuario
        return optionalBook.get();
    }

    public boolean doesBookExistByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}
