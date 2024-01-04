package com.fundaleonREST.fundaleonapirest.service;

import com.fundaleonREST.fundaleonapirest.model.Customer;
import com.fundaleonREST.fundaleonapirest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        System.out.println("Guardando cliente en la base de datos" + customer.toString());
        // Si no existe, guardar el usuario
        return customerRepository.save(customer);
    }

    public Customer editCustomer(Customer user) {
        // Verificar si el usuario existe en la base de datos
        Optional<Customer> optionalCustomer = customerRepository.findById(user.getId());
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Recopilar datos del usuario
        Customer customerToEdit = optionalCustomer.get();
        customerToEdit.setIdentification(user.getIdentification());
        customerToEdit.setEmail(user.getEmail());
        customerToEdit.setFull_name(user.getFull_name());
        customerToEdit.setPhone_number(user.getPhone_number());
        customerToEdit.setAddress(user.getAddress());
        customerToEdit.setJob(user.getJob());
        customerToEdit.setUpdated_at(new Date());



        // Guardar el usuario
        return customerRepository.save(customerToEdit);
    }

    public Customer deleteCustomerById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Recopilar datos del usuario
        Customer customer = optionalCustomer.get();

        // Eliminar el usuario
        customerRepository.deleteById(id);

        // Devolver los datos del usuario eliminado
        return customer;
    }

    public Customer getCustomerById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Devolver los datos del usuario
        return optionalCustomer.get();
    }

    public boolean doesCustomerExistByEmail(String email) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);
        return existingCustomer.isPresent();
    }
}
