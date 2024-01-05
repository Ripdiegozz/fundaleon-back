package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.configuration.ApiResponse;
import com.fundaleonREST.fundaleonapirest.model.User;
import com.fundaleonREST.fundaleonapirest.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

@RestController
@CrossOrigin // Permitir solicitudes CORS para todo el controlador
@RequestMapping("/user")
public class UserController {
    // Inyectar el servicio
    private final UserService userService;
    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user) {
        // Verificar si el usuario existe en la base de datos
        if (userService.getUserById(user.getId()) != null) {
            // Construir respuesta en caso de error
            ApiResponse response = new ApiResponse("400", "Un usuario ya existe con este ID. Por favor, inicia sesión.", null);
            return ResponseEntity.badRequest().body(response);
        }
        // Guardar el usuario
        User userRegistered = userService.saveUser(user);
        // Construir la respuesta
        ApiResponse response = new ApiResponse("201", "Guardando usuario en la base de datos", userRegistered);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponse> editUser(@RequestBody User user) {
        System.out.println("Editando usuario en la base de datos" + user.toString());
        // Verificar si el usuario existe en la base de datos
        if (userService.getUserById(user.getId()) == null) {
            // Construir respuesta en caso de error
            ApiResponse response = new ApiResponse("400", "No existe este usuario en la base de datos.", null);
            return ResponseEntity.badRequest().body(response);
        }
        // Guardar el usuario editado
        User userEdited = userService.editUser(user);
        // Construir la respuesta
        ApiResponse response = new ApiResponse("200", "Editando usuario en la base de datos", userEdited);
        return ResponseEntity.ok(response);
    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        // Encontrar Usuario
        User user = userService.getUserById(id);
        // Construir la respuesta
        ApiResponse response = new ApiResponse("200", "Obteniendo información del usuario con ID: " + id, user);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable UUID id) {
        // Obtener el usuario
        User user = userService.getUserById(id);
        // Eliminar Usuario
        userService.deleteUserById(id);
        // Construir la respuesta
        ApiResponse response = new ApiResponse("200", "Eliminando usuario con ID: " + id, user);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok(response);
    }
}
