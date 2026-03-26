package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
}
