package br.insper.iam.security;

import br.insper.iam.login.LoginService;
import br.insper.iam.usuario.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        if (path.equals("/api/usuario") && method.equals("POST")) {
            filterChain.doFilter(request, response);
        } else if (path.equals("/api/login")) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader("Authorization");

            loginService.validateToken(token);
            filterChain.doFilter(request, response);
        }
    }
}
