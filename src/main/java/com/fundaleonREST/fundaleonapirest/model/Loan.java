package com.fundaleonREST.fundaleonapirest.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "loan")
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID customer_id;
    private UUID book_id;
    private Date loan_date = new Date();
    private Date return_date_expected = null;
    private String status = "Prestado";

    public Loan() {
    }

    public Loan(UUID customer_id, UUID book_id) {
        this.customer_id = customer_id;
        this.book_id = book_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public UUID getBook_id() {
        return book_id;
    }

    public void setBook_id(UUID book_id) {
        this.book_id = book_id;
    }

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date) {
        this.loan_date = loan_date;
    }

    public Date getReturn_date_expected() {
        return return_date_expected;
    }

    public void setReturn_date_expected(Date return_date_expected) {
        this.return_date_expected = return_date_expected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", book_id=" + book_id +
                ", loan_date=" + loan_date +
                ", return_date_expected=" + return_date_expected +
                ", status='" + status + '\'' +
                '}';
    }
}
