package com.thewalking.shop.security.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(email).filter(s -> !s.isEmpty());
    }

}
