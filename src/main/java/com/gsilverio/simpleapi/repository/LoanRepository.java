package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Boolean existsByUserIdAndBookId(Integer userId, Integer bookId);
}
