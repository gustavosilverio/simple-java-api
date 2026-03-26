package com.gsilverio.simpleapi.repository.specification;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.dto.request.book.BookFilterRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification {
    public static Specification<Book> withFilter(BookFilterRequest filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filter.title())){
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + filter.title().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(filter.author())){
                predicates.add(cb.like(cb.lower(root.get("author")), "%" + filter.author().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(filter.category())){
                predicates.add(cb.like(cb.lower(root.get("category")), "%" + filter.category().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}