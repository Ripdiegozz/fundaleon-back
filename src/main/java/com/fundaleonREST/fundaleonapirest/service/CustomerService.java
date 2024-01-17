package com.fundaleonREST.fundaleonapirest.service;

import com.fundaleonREST.fundaleonapirest.model.Customer;
import com.fundaleonREST.fundaleonapirest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public void deleteCustomerById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID.");
        }
        // Eliminar el usuario
        customerRepository.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        // Obtener todos los usuarios
        return customerRepository.findAll();
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

    public Customer getCustomerByEmail(String email) {
        // Verificar si el usuario existe en la base de datos
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese email");
        }

        // Devolver los datos del usuario
        return optionalCustomer.get();
    }

    public Customer getCustomerByIdentification(String identification) {
        // Verificar si el usuario existe en la base de datos
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByIdentification(identification);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con esa cédula");
        }

        // Devolver los datos del usuario
        return optionalCustomer.get();
    }

    public boolean doesCustomerExistByEmail(String email) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);
        return existingCustomer.isPresent();
    }

    public long countAllCustomers() {
        return customerRepository.count();
    }

    public Map<String, Object> getCustomersCountByMonth() {
        // Obtener todos los usuarios
        List<Customer> customersFromDb = customerRepository.findAll();

        // Filtrar solo los usuarios creados este año
        customersFromDb.removeIf(customer -> customer.getCreated_at().getYear() != new Date().getYear());

        // Inicializar el objeto con los recuentos y las etiquetas de los meses
        Map<String, Object> result = new HashMap<>();

        // Inicializar el mapa de recuentos por mes
        Map<String, Integer> countByMonth = new TreeMap<>(); // Usar TreeMap para ordenar las claves

        // Hacer un Map con el key del número de mes y el valor de Nombre del mes para ordenarlos
        Map<String, String> monthNames = new HashMap<>();
        monthNames.put("01", "Enero");
        monthNames.put("02", "Febrero");
        monthNames.put("03", "Marzo");
        monthNames.put("04", "Abril");
        monthNames.put("05", "Mayo");
        monthNames.put("06", "Junio");
        monthNames.put("07", "Julio");
        monthNames.put("08", "Agosto");
        monthNames.put("09", "Septiembre");
        monthNames.put("10", "Octubre");
        monthNames.put("11", "Noviembre");
        monthNames.put("12", "Diciembre");

        // Recorrer la lista de usuarios
        for (Customer customer : customersFromDb) {
            // Ver cuando fue creado el cliente obteniendo el mes, ex: 1973-01-16 09:04:49.0
            String month = customer.getCreated_at().toString().split("-")[1];

            // Incrementar el recuento del mes
            countByMonth.put(month, countByMonth.getOrDefault(month, 0) + 1);
        }

        // Agregar el recuento por mes al resultado
        result.put("count", countByMonth);

        // Obtener la lista de nombres de meses correspondientes a los meses presentes en count
        List<String> monthLabels = countByMonth.keySet().stream()
                .sorted(Comparator.comparingInt(Integer::parseInt)) // Ordenar claves numéricamente
                .map(monthNames::get)
                .collect(Collectors.toList());

        // Agregar la lista de nombres de meses al resultado
        result.put("monthLabels", monthLabels);

        return result;
    }
}