package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.model.Customer;
import com.fundaleonREST.fundaleonapirest.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    // Inyectar el servicio
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService ) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Customer Customer) {
        // Verificar si el usuario existe en la base de datos
        if (customerService.doesCustomerExistByEmail(Customer.getEmail())) {
            return ResponseEntity.badRequest().body("Un usuario ya existe con este correo electrónico. Por favor, use otro correo electrónico.");
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Customer CustomerRegistered = customerService.saveCustomer(Customer);
        return ResponseEntity.ok("Guardando usuario en la base de datos\n" + CustomerRegistered.toString());
    }
    @PutMapping("/edit")
    public ResponseEntity editCustomer(@RequestBody Customer Customer) {
        // Verificar si el usuario existe en la base de datos
        if (customerService.getCustomerById(Customer.getId()) == null) {
            return ResponseEntity.badRequest().body("No existe este usuario en la base de datos.");
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Customer CustomerEdited = customerService.editCustomer(Customer);
        return ResponseEntity.ok("Editando usuario en la base de datos\n" + CustomerEdited.toString());
    }
    @GetMapping ("/get/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable UUID id) {
        // Encontrar Usuario
        Customer Customer = customerService.getCustomerById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Obteniendo información del usuario con ID: " + id + "\n" + Customer.toString());
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable UUID id) {
        // Obtener el usuario
        Customer Customer = customerService.getCustomerById(id);
        // Eliminar Usuario
        customerService.deleteCustomerById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Eliminando usuario con ID: " + id + "\n" + Customer.toString());
    }
}
