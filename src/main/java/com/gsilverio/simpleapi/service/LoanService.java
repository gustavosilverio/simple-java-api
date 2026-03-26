package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Loan;
import com.gsilverio.simpleapi.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository repository;

    @Transactional public Loan save(Loan loan) {
        return repository.save(loan);
    }
}
