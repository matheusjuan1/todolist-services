package br.com.matheusjuan.todolist.middleware;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.matheusjuan.todolist.error.AuthExceptions.JWTVerificationException;
import br.com.matheusjuan.todolist.error.UserExceptions.UserNotFoundException;
import br.com.matheusjuan.todolist.model.User;
import br.com.matheusjuan.todolist.model.dto.error.ErrorResponseDTO;
import br.com.matheusjuan.todolist.repository.UserRepository;
import br.com.matheusjuan.todolist.security.JWTCreator;
import br.com.matheusjuan.todolist.security.SecurityConfig;
import br.com.matheusjuan.todolist.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCreator jwtCreator;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!isPublicEndpoint(request)) {
            try {
                String token = recoveryToken(request);
                if (token != null) {
                    String subject = jwtCreator.getSubjectFromToken(token);
                    User user = userRepository.findById(UUID.fromString(subject))
                            .orElseThrow(() -> new UserNotFoundException());
                    UserDetailsImpl userDetails = new UserDetailsImpl(user);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            null,
                            userDetails.getAuthorities());

                    request.setAttribute("idUser", subject);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new JWTVerificationException();
                }
            } catch (UserNotFoundException e) {
                handleException(response, 2001, e.getMessage(), "USER_NOT_FOUND");
                return;
            } catch (JWTVerificationException e) {
                handleException(response, 1001, e.getMessage(), "INVALID_JWT");
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace(
                    "Bearer ", "");
        }
        return null;
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.asList(SecurityConfig.PUBLIC_ENDPOINTS).stream().anyMatch(requestURI::startsWith);
    }

    private void handleException(HttpServletResponse response, int errorCode, String message, String errorType)
            throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(errorCode, message, errorType);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
