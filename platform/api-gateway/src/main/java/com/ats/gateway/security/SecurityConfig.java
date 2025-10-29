// com/ats/gateway/security/SecurityConfig.java
package com.ats.gateway.security;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity; import org.springframework.security.web.server.SecurityWebFilterChain;
@EnableWebFluxSecurity @Configuration
class SecurityConfig {
    @Bean SecurityWebFilterChain filter(ServerHttpSecurity http){
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex->ex.anyExchange().permitAll()) // tighten in Phase 1
                .build();
    }
}
