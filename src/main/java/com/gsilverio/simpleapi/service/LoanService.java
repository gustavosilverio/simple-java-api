package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.domain.Book;
import com.gsilverio.simpleapi.domain.Loan;
import com.gsilverio.simpleapi.domain.User;
import com.gsilverio.simpleapi.domain.dto.loan.request.CreateLoanRequest;
import com.gsilverio.simpleapi.repository.LoanRepository;
import com.gsilverio.simpleapi.security.CurrentUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository repository;

    private final BookService bookService;

    private final CurrentUserService currentUserService;

    public Boolean findByUserId(Integer userId){
        return repository.existsByUserId(userId);
    }

    @Transactional
    public Loan save(CreateLoanRequest request) {
        Book book = bookService.findById(request.bookId());

        if (book.getAvailableUnits() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "book unavailable");

        User user = currentUserService.getCurrentUser();

        if (findByUserId(user.getId()))
            throw new ResponseStatusException()

        book.setAvailableUnits(book.getAvailableUnits() - 1);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setExpectedReturnDate(LocalDate.now().plusDays(15));

        return repository.save(loan);
    }
}
