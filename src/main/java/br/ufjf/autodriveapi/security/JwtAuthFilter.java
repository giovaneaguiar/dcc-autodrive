package br.ufjf.autodriveapi.security;

import br.ufjf.autodriveapi.service.UsuarioAdminService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioAdminService usuarioAdminService;

    public JwtAuthFilter( JwtService jwtService, UsuarioAdminService usuarioAdminService) {
        this.jwtService = jwtService;
        this.usuarioAdminService = usuarioAdminService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            if(isValid){
                String loginUsuarioAdmin = jwtService.obterLoginUsuarioAdmin(token);
                UserDetails usuarioAdmin = usuarioAdminService.loadUserByUsername(loginUsuarioAdmin);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(usuarioAdmin,null,
                        usuarioAdmin.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}