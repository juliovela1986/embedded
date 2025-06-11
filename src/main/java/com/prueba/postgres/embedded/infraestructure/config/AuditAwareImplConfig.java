package com.prueba.postgres.embedded.infraestructure.config;


import com.prueba.postgres.embedded.infraestructure.dto.UserKeyCloackDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImplConfig implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(auth -> auth.getPrincipal() instanceof UserKeyCloackDto user ? Long.valueOf(user.getEmpleoyeNumber())
            : null);
  }

  public Long getCurrentUserId() {
    return getCurrentAuditor().orElse(null);
  }
  
}
