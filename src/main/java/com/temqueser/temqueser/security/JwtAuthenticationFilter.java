package com.temqueser.temqueser.security;

import com.temqueser.temqueser.services.ClienteServices;
import com.temqueser.temqueser.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private ClienteServices clienteServices;
    
   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Liberar requisições OPTIONS (preflight CORS)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

            if (jwtService.isTokenValid(token)) {
                Claims claims = jwtService.extractClaims(token);
                Long userId = Long.parseLong(claims.getSubject());
                // Pode setar o usuário logado em uma thread local ou request attribute
                request.setAttribute("UserId", userId);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // não chama o filterChain, bloqueia a requisição
            }
        }
        filterChain.doFilter(request, response);
    }

}
