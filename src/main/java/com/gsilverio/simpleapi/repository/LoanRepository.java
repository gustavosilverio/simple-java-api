package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Boolean existsByUserId(Integer userId);
}
