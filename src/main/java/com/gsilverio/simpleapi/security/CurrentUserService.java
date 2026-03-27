package com.gsilverio.simpleapi.security;

import com.gsilverio.simpleapi.domain.User;
import com.gsilverio.simpleapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserService userService;

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return (String) authentication.getPrincipal();
    }

    public User getCurrentUser() {
        String email = getCurrentUserEmail();

        if (email == null) {
            throw new RuntimeException("user not authenticated");
        }

        return userService.findUserByEmail(email);
    }
}