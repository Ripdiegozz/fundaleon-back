package com.fundaleonREST.fundaleonapirest.controller;

import com.fundaleonREST.fundaleonapirest.configuration.ApiResponse;
import com.fundaleonREST.fundaleonapirest.model.Loan;
import com.fundaleonREST.fundaleonapirest.service.LoanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

        private final LoanService loanService;

        @Autowired
        public LoanController(LoanService loanService) {
            this.loanService = loanService;
        }

        @PostMapping("/create")
        public ResponseEntity<ApiResponse> createLoan(@RequestBody Loan loan) {
            return ResponseEntity.ok(new ApiResponse("200", "Registrando préstamo en la base de datos", loanService.createLoan(loan)));
        }

        @PutMapping("/edit")
        public ResponseEntity<ApiResponse> editLoan(@RequestBody Loan loan) {
            return ResponseEntity.ok(new ApiResponse("200", "Actualizando préstamo en la base de datos", loanService.editLoan(loan)));
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<ApiResponse> deleteLoanById(@PathVariable UUID id) {
            loanService.deleteLoanById(id);
            return ResponseEntity.ok(new ApiResponse("200", "Eliminando préstamo de la base de datos", null));
        }

        @GetMapping("/get/all")
        public ResponseEntity<ApiResponse> getAllLoans() {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los préstamos de la base de datos", loanService.getAllLoans()));
        }

        @GetMapping("/get/{id}")
        public ResponseEntity<ApiResponse> getLoanById(@PathVariable UUID id) {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo préstamo de la base de datos", loanService.getLoanById(id)));
        }

        @GetMapping("/get/all/customer/{id}")
        public ResponseEntity<ApiResponse> getAllLoansByCustomerId(@PathVariable UUID id) {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los préstamos de la base de datos", loanService.getLoansByCustomerId(id)));
        }

        @GetMapping("/get/all/book/{id}")
        public ResponseEntity<ApiResponse> getAllLoansByBookId(@PathVariable UUID id) {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los préstamos de la base de datos", loanService.getLoansByBookId(id)));
        }

        @GetMapping("/get/all/status/{status}")
        public ResponseEntity<ApiResponse> getAllLoansByStatus(@PathVariable String status) {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los préstamos de la base de datos", loanService.getLoansByStatus(status)));
        }

        @GetMapping("/get/all/date/{date}")
        public ResponseEntity<ApiResponse> getAllLoansByDate(@PathVariable Date date) {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los préstamos de la base de datos", loanService.getLoansByLoanDate(date)));
        }

        @GetMapping("/get/all/return/{return}")
        public ResponseEntity<ApiResponse> getAllLoansByReturnDate(@PathVariable Date date) {
            return ResponseEntity.ok(new ApiResponse("200", "Obteniendo todos los préstamos de la base de datos", loanService.getLoansByReturnDateExpected(date)));
        }
}
