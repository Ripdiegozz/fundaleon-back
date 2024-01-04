package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.model.Book;
import com.fundaleonREST.fundaleonapirest.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    // Inyectar el servicio
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService ) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book) {
        // Verificar si el usuario existe en la base de datos
        if (bookService.doesBookExistByIsbn(book.getIsbn())) {
            return ResponseEntity.badRequest().body("Un libro ya existe con este ISBN. Por favor, use otro ISBN.");
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Book bookRegistered = bookService.saveBook(book);
        return ResponseEntity.ok("Guardando libro en la base de datos\n" + bookRegistered.toString());
    }
    @PutMapping("/edit")
    public ResponseEntity editUser(@RequestBody Book book) {
        // Verificar si el usuario existe en la base de datos
        if (bookService.getBookById(book.getId()) == null) {
            return ResponseEntity.badRequest().body("No existe un libro en la base de datos con este ISBN.");
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Book bookEdited = bookService.editBook(book);
        return ResponseEntity.ok("Editando usuario en la base de datos\n" + bookEdited.toString());
    }
    @GetMapping ("/get/{id}")
    public ResponseEntity<String> getUserById(@PathVariable UUID id) {
        // Encontrar Libro
        Book book = bookService.getBookById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Obteniendo información del libro con ID: " + id + "\n" + book.toString());
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID id) {
        // Obtener el libro
        Book book = bookService.getBookById(id);
        // Eliminar Libro
        bookService.deleteBookById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Eliminando libro con ID: " + id + "\n" + book.toString());
    }
}
