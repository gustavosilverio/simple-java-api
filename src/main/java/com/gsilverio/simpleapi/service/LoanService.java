package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.domain.Book;
import com.gsilverio.simpleapi.domain.Loan;
import com.gsilverio.simpleapi.domain.User;
import com.gsilverio.simpleapi.domain.dto.loan.request.CreateLoanRequest;
import com.gsilverio.simpleapi.domain.dto.loan.response.CreateLoanResponse;
import com.gsilverio.simpleapi.repository.LoanRepository;
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

    private final UserService userService;

    public Boolean existsByUserIdAndBookId(Integer userId, Integer bookId){
        return repository.existsByUserIdAndBookId(userId, bookId);
    }

    @Transactional
    public CreateLoanResponse save(CreateLoanRequest request) {
        Book book = bookService.findById(request.bookId());

        if (book.getAvailableUnits() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "book unavailable");

        if (!userService.existsById(request.userId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");

        if (existsByUserIdAndBookId(request.userId(), request.bookId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "book already loaned to this user");

        User user = userService.findById(request.userId());

        book.setAvailableUnits(book.getAvailableUnits() - 1);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setExpectedReturnDate(LocalDate.now().plusDays(15));

        Loan createdLoan = repository.save(loan);

        return new CreateLoanResponse(
                createdLoan.getId(),
                createdLoan.getBook().getId(),
                createdLoan.getUser().getId()
        );
    }
}
