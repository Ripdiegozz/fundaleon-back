package com.fundaleonREST.fundaleonapirest.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "\"User\"")
@Table(name = "\"User\"", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    // TimeStamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();
    @Column(name = "password", nullable = true) // Ajusta la longitud según tus necesidades
    private String password;
    @Column(name = "email", nullable = true) // Ajusta la longitud según tus necesidades
    private String email;
    @Column(name = "updated_at", nullable = true, updatable = true, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP") // Ajusta la longitud según tus necesidades
    private Date updated_at = null;
    @Column(name = "full_name", nullable = true) // Ajusta la longitud según tus necesidades
    private String full_name;
    @Column(name = "phone_number", nullable = true) // Ajusta la longitud según tus necesidades
    private String phone_number;
    @Column(name = "cedula", nullable = true) // Ajusta la longitud según tus necesidades
    private String cedula;
    @Column(name = "role", nullable = true) // Ajusta la longitud según tus necesidades
    private String role;

    public User() {
    }

    public User(String password, String email, String full_name, String phone_number, String cedula, String role) {
        this.cedula = cedula;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCedula() {
        return cedula;
    }

    public String getRole() {
        return role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", created_at='" + createdAt + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", cedula='" + cedula + '\'' +
                ", role=" + role +
                '}';
    }

}
