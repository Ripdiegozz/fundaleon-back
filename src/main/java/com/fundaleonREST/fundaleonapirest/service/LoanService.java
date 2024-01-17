package com.fundaleonREST.fundaleonapirest.service;

import com.fundaleonREST.fundaleonapirest.model.Loan;
import com.fundaleonREST.fundaleonapirest.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Loan loan) {
        System.out.println("Guardando préstamo en la base de datos" + loan.toString());
        // Si no existe, guardar el préstamo
        return loanRepository.save(loan);
    }

    public Loan editLoan(Loan loan) {
        // Verificar si el préstamo existe en la base de datos
        Optional<Loan> optionalLoan = loanRepository.findById(loan.getId());
        if (optionalLoan.isEmpty()) {
            throw new RuntimeException("El préstamo no existe en la base de datos con ese ID");
        }

        // Recopilar datos del préstamo
        Loan loanToEdit = optionalLoan.get();
        loanToEdit.setCustomer_id(loan.getCustomer_id());
        loanToEdit.setBook_id(loan.getBook_id());
        loanToEdit.setLoan_date(loan.getLoan_date());
        loanToEdit.setReturn_date_expected(loan.getReturn_date_expected());
        loanToEdit.setStatus(loan.getStatus());

        // Guardar el préstamo
        return loanRepository.save(loanToEdit);
    }

    public void deleteLoanById(UUID id) {
        // Verificar si el préstamo existe en la base de datos
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isEmpty()) {
            throw new RuntimeException("El préstamo no existe en la base de datos con ese ID.");
        }
        // Eliminar el préstamo
        loanRepository.deleteById(id);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(UUID id) {
        // Verificar si el préstamo existe en la base de datos
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isEmpty()) {
            throw new RuntimeException("El préstamo no existe en la base de datos con ese ID.");
        }
        // Obtener el préstamo
        return optionalLoan.get();
    }

    public List<Loan> getLoansByCustomerId(UUID customer_id) {
        // Obtener todos los préstamos
        List<Loan> loans = loanRepository.findAll();
        // Filtrar los préstamos por customer_id
        return loans.stream().filter(loan -> loan.getCustomer_id().equals(customer_id)).collect(Collectors.toList());
    }

    public List<Loan> getLoansByBookId(UUID book_id) {
        // Obtener todos los préstamos
        List<Loan> loans = loanRepository.findAll();
        // Filtrar los préstamos por book_id
        return loans.stream().filter(loan -> loan.getBook_id().equals(book_id)).collect(Collectors.toList());
    }

    public List<Loan> getLoansByStatus(String status) {
        // Obtener todos los préstamos
        List<Loan> loans = loanRepository.findAll();
        // Filtrar los préstamos por status
        return loans.stream().filter(loan -> loan.getStatus().equals(status)).collect(Collectors.toList());
    }

    public List<Loan> getLoansByLoanDate(Date loan_date) {
        // Obtener todos los préstamos
        List<Loan> loans = loanRepository.findAll();
        // Filtrar los préstamos por loan_date
        return loans.stream().filter(loan -> loan.getLoan_date().equals(loan_date)).collect(Collectors.toList());
    }

    public List<Loan> getLoansByReturnDateExpected(Date return_date_expected) {
        // Obtener todos los préstamos
        List<Loan> loans = loanRepository.findAll();
        // Filtrar los préstamos por return_date_expected
        return loans.stream().filter(loan -> loan.getReturn_date_expected().equals(return_date_expected)).collect(Collectors.toList());
    }
}