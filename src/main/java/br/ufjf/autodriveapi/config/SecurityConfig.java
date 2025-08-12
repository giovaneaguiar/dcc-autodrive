package br.ufjf.autodriveapi.config;

import br.ufjf.autodriveapi.security.JwtAuthFilter;
import br.ufjf.autodriveapi.security.JwtService;
import br.ufjf.autodriveapi.service.UsuarioAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioAdminService usuarioAdminService;

    @Autowired
    private JwtService jwtService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioAdminService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioAdminService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/anuncios/**")
                .hasAnyRole("ADMIN")
                //.authenticated()
                .antMatchers("/api/v1/categorias/**")
                .permitAll()
                .antMatchers("/api/v1/empresas/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/api/v1/favoritos/**")
                .permitAll()
                .antMatchers("/api/v1/financiamentos/**")
                .permitAll()
                .antMatchers("/api/v1/fotos/**")
                .permitAll()
                .antMatchers("/api/v1/marcas/**")
                .permitAll()
                .antMatchers("/api/v1/notificacoes/**")
                .permitAll()
                .antMatchers( "/api/v1/opcionais/**")
                .permitAll()
                .antMatchers( "/api/v1/pagamentos/**")
                .permitAll()
                .antMatchers( "/api/v1/propostas/**")
                .permitAll()
                .antMatchers( "/api/v1/tipos/**")
                .permitAll()
                .antMatchers( "/api/v1/usuarios/**")
                .hasAnyRole("ADMIN")
                .antMatchers( "/api/v1/veiculos/**")
                .permitAll()
                .antMatchers( "/api/v1/vendas/**")
                .hasAnyRole("ADMIN")
                .antMatchers( "/api/v1/usuariosAdmin/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}