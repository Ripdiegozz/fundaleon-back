package com.fundaleonREST.fundaleonapirest.service;

import com.fundaleonREST.fundaleonapirest.model.Book;
import com.fundaleonREST.fundaleonapirest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate; // import the LocalDate class
import java.util.List;
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
        // Verificar si el libro existe en la base de datos
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Ya existe un libro con este ISBN. Por favor, use otro ISBN.");
        }

        // Si no existe, guardar el libro
        return bookRepository.save(book);
    }

    public Book editBook(Book book) {
        // Verificar si el libro existe en la base de datos
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
        System.out.println(book.getPublication_year());
        bookToEdit.setPublisher(book.getPublisher());
        bookToEdit.setUpdated_at(new Date());


        // Guardar el libro
        return bookRepository.save(bookToEdit);
    }

    public void deleteBookById(UUID id) {
        // Verificar si el libro existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("El Libro no existe en la base de datos con ese ID");
        }

        // Eliminar el libro
        bookRepository.deleteById(id);
    }

    public void changeBookStatus(UUID id, boolean status) {
        // Verificar si el libro existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("El libro no existe en la base de datos con ese ID");
        }

        // Recopilar datos del libro
        Book bookToEdit = optionalBook.get();
        bookToEdit.setStatus(status);
        bookToEdit.setUpdated_at(new Date());

        // Guardar el libro
        bookRepository.save(bookToEdit);
    }

    public Book getBookById(UUID id) {
        // Verificar si el libro existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Devolver los datos del usuario
        return optionalBook.get();
    }

    public Book getBookByIsbn(String isbn) {
        // Verificar si el libro existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        // Devolver los datos del usuario
        return optionalBook.get();
    }

    public Book getBookByTitle(String title) {
        // Verificar si el libro existe en la base de datos
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        // Devolver los datos del usuario
        return optionalBook.get();
    }

    public List<Book> getBooksByAuthor(String author) {
        // Devolver los datos del usuario
        return bookRepository.findBooksByAuthor(author);
    }

    public List<Book> getAllBooks() {
        // Devolver los libros
        return bookRepository.findAll();
    }

    public Long countAllBooks() {
        // Devolver el número de libros
        return bookRepository.count();
    }

    public boolean doesBookExistByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}
