package com.gsilverio.simpleapi.model.dto.response.book;

import com.gsilverio.simpleapi.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetByIdResponse extends Book {
    private Boolean isAvailable;

    public GetByIdResponse(Book book) {
        this.isAvailable = book.getAvailableUnits() > 0;
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setIsbn(book.getIsbn());
        this.setPublicationYear(book.getPublicationYear());
        this.setAvailableUnits(book.getAvailableUnits());
        this.setCategory(book.getCategory());
    }
}
