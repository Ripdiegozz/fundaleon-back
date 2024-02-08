package com.fundaleonREST.fundaleonapirest.service;

import com.fundaleonREST.fundaleonapirest.model.User;
import com.fundaleonREST.fundaleonapirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.UUID;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        System.out.println("Guardando usuario en la base de datos" + user.toString());
        // Si no existe, guardar el usuario
        return userRepository.save(user);
    }

    public User editUser(User user) {
        System.out.println("Editando usuario en la base de datos" + user.toString());
        // Verificar si el usuario existe en la base de datos
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Recopilar datos del usuario
        User userToEdit = optionalUser.get();
        userToEdit.setCedula(user.getCedula());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setFull_name(user.getFull_name());
        userToEdit.setPhone_number(user.getPhone_number());
        userToEdit.setRole(user.getRole());
        userToEdit.setUpdated_at(new Date());

        // Guardar el usuario
        return userRepository.save(userToEdit);
    }

    public void deleteUserById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("El usuario no existe en la base de datos con ese ID");
        }

        // Eliminar el usuario
        userRepository.deleteById(id);
    }

    public User getUserById(UUID id) {
        // Verificar si el usuario existe en la base de datos
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Iterable<User> getAllUsers() {
        // Obtener todos los usuarios
        return userRepository.findAll();
    }
}
