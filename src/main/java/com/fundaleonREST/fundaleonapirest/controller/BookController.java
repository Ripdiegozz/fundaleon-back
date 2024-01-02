package com.fundaleonREST.fundaleonapirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping("/book")
    public String hello() {
        return """
                Book: 100 años de soledad
                Author: Gabriel García Márquez
                Year: 1967
                Genre: Realismo mágico
                Language: Español
                Country: Colombia
                Publisher: Editorial Sudamericana
                """;
    }
}
