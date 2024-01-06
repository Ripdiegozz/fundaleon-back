package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.configuration.ApiResponse;
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
    public ResponseEntity<ApiResponse> addBook(@RequestBody Book book) {
        // Verificar si el usuario existe en la base de datos
        if (bookService.doesBookExistByIsbn(book.getIsbn())) {
            return ResponseEntity.badRequest().body(new ApiResponse("400", "Ya existe un libro con este ISBN. Por favor, use otro ISBN.", null));
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Book bookRegistered = bookService.saveBook(book);
        return ResponseEntity.ok(new ApiResponse("200", "Registrando libro en la base de datos", bookRegistered));
    }
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse> getAllBooks() {
        // Obtener todos los libros
        return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los libros de la base de datos", bookService.getAllBooks()));
    }
    @PutMapping("/edit")
    public ResponseEntity<ApiResponse> editUser(@RequestBody Book book) {
        // Verificar si el usuario existe en la base de datos
        if (bookService.getBookById(book.getId()) == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("400", "No se encontró ningún libro con este ID. Por favor, use otro ID.", null));
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Book bookEdited = bookService.editBook(book);
        return ResponseEntity.ok(new ApiResponse("200", "Editando libro en la base de datos", bookEdited));
    }
    @GetMapping ("/get/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        // Encontrar Libro
        Book book = bookService.getBookById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok(new ApiResponse("200", "Obteniendo libro con ID: " + id, book));
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable UUID id) {
        // Obtener el libro
        Book book = bookService.getBookById(id);
        // Verificar si el libro existe en la base de datos
        if (book == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("400", "No se encontró ningún libro con este ID. Por favor, use otro ID.", null));
        }
        // Eliminar Libro
        bookService.deleteBookById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok(new ApiResponse("200", "Eliminando libro con ID: " + id, book));
    }
}
