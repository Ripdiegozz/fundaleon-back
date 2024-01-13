package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.configuration.ApiResponse;
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
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody Customer Customer) {
        // Verificar si el usuario existe en la base de datos
        if (customerService.doesCustomerExistByEmail(Customer.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse("400", "Ya existe un usuario con este correo electrónico", null));
        }
        // Additional validations or logic before saving
        // Guardar el usuario
        Customer CustomerRegistered = customerService.saveCustomer(Customer);
        return ResponseEntity.ok(new ApiResponse("200", "Guardando usuario en la base de datos", CustomerRegistered.toString()));
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponse> editCustomer(@RequestBody Customer Customer) {
        // Verificar si el usuario existe en la base de datos
        if (customerService.getCustomerById(Customer.getId()) == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("400", "No existe un usuario con este ID.", null));
        }
        // Guardar el usuario
        Customer CustomerEdited = customerService.editCustomer(Customer);
        return ResponseEntity.ok(new ApiResponse("200", "Guardando clientes en la base de datos.", CustomerEdited.toString()));
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse> getAllBooks() {
        // Obtener todos los libros
        return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los clientes de la base de datos.", customerService.getAllCustomers()));
    }

    @GetMapping("get/all/count")
    public ResponseEntity<ApiResponse> countAllBooks() {
        // Obtener todos los libros
        return ResponseEntity.ok(new ApiResponse("200", "Contando todos los clientes de la base de datos.", customerService.countAllCustomers()));
    }

    @GetMapping("get/count/month")
    public ResponseEntity<ApiResponse> getCustomersCountByMonth() {
        // Obtener todos los libros
        return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los clientes de la base de datos por mes.", customerService.getCustomersCountByMonth()));
    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable UUID id) {
        // Encontrar Usuario
        Customer Customer = customerService.getCustomerById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Obteniendo información del cliente con ID: " + id + "\n" + Customer.toString());
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable UUID id) {
        // Obtener el usuario
        Customer Customer = customerService.getCustomerById(id);
        // Eliminar Usuario
        customerService.deleteCustomerById(id);
        // Aquí puedes usar el ID para buscar el usuario en la base de datos u realizar otras operaciones
        return ResponseEntity.ok("Eliminando cliente con ID: " + id + "\n" + Customer.toString());
    }
}
