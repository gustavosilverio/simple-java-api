package com.gsilverio.simpleapi.mapper;

import com.gsilverio.simpleapi.domain.User;
import com.gsilverio.simpleapi.domain.dto.user.request.UserRequest;
import com.gsilverio.simpleapi.domain.dto.user.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequest request);
}
