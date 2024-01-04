package com.fundaleonREST.fundaleonapirest.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String identification;
    private String full_name;
    private String email;
    private String phone_number;
    private Date created_at = new Date();
    private Date updated_at = null;
    private String address;
    private String job;

    public Customer() {
    }

    public Customer(String identification, String full_name, String email, String phone_number, String address, String job) {
        this.identification = identification;
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.job = job;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", identification='" + identification + '\'' +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
