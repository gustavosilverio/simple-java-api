package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByUserIdAndBookId(Integer userId, Integer bookId);

    Optional<Loan> findByUserIdAndBookIdAndActualReturnDateIsNull(Integer userId, Integer bookId);
}
