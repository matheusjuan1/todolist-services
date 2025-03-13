package br.com.matheusjuan.todolist.middleware;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import br.com.matheusjuan.todolist.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            System.out.println("passou por aqui");
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token não encontrado");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtService.validateToken(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token inválido");
            return;
        }

        String userId = jwtService.getUserIdFromToken(token);

        if (userId != null) {
            request.setAttribute("idUser", userId);
        }

        filterChain.doFilter(request, response);
    }
}
