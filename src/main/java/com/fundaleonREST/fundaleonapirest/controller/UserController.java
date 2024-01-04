package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.model.User;
import com.fundaleonREST.fundaleonapirest.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    // Inyectar el servicio
    private final UserService userService;
    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user) {
        // Verificar si el usuario existe en la base de datos
        if (userService.doesUserExistByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Un usuario ya existe con este correo electrónico. Por favor, use otro correo electrónico.");
        }
        // Additional validations or logic before saving
        // Hash password
        user.setPassword(userService.hashPassword(user.getPassword()));
        // Guardar el usuario
        User userRegistered = userService.saveUser(user);
        return ResponseEntity.ok("Guardando usuario en la base de datos\n" + userRegistered.toString());
    }
    @PutMapping("/edit")
    public ResponseEntity editUser(@RequestBody User user) {
        System.out.println("Editando usuario en la base de datos" + user.toString());
        // Verificar si el usuario existe en la base de datos
        if (userService.getUserById(user.getId()) == null) {
            return ResponseEntity.badRequest().body("No existe este usuario en la base de datos.");
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        User userEdited = userService.editUser(user);
        return ResponseEntity.ok("Editando usuario en la base de datos\n" + userEdited.toString());
    }
    @GetMapping ("/get/{id}")
    public ResponseEntity<String> getUserById(@PathVariable UUID id) {
        // Encontrar Usuario
        User user = userService.getUserById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Obteniendo información del usuario con ID: " + id + "\n" + user.toString());
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID id) {
        // Obtener el usuario
        User user = userService.getUserById(id);
        // Eliminar Usuario
        userService.deleteUserById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Eliminando usuario con ID: " + id + "\n" + user.toString());
    }
}
