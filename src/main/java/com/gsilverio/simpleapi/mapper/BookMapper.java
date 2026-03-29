package com.gsilverio.simpleapi.mapper;

import com.gsilverio.simpleapi.domain.Book;
import com.gsilverio.simpleapi.domain.dto.book.request.CreateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "isbn", expression = "java(request.isbn().replace(\"-\", \"\"))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book toEntity(CreateBookRequest request);
}
