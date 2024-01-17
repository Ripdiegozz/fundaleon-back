package com.fundaleonREST.fundaleonapirest.repository;

import com.fundaleonREST.fundaleonapirest.model.Customer;
import com.fundaleonREST.fundaleonapirest.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    Optional<Loan> findById(UUID id);
}
