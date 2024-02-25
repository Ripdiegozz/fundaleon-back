package com.fundaleonREST.fundaleonapirest.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
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
    private boolean status = true;

    public Customer() {
    }

    public Customer(String identification, String full_name, String email, String phone_number, String address,
                    String job, Boolean status) {
        this.identification = identification;
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.job = job;
        this.status = status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
                ", status=" + status +
                '}';
    }
}
