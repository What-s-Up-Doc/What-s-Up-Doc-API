package fr.esgi.whatsupdocapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/account").permitAll()
                .antMatchers("/api/account/**").permitAll()
                .antMatchers("/api/diagnosis/**").permitAll()
                .antMatchers("/api/appointment/**").hasAnyRole("DOCTOR", "PATIENT")
                .antMatchers("/api/doctors/**").hasRole("DOCTOR")
                .antMatchers(HttpMethod.GET,"/api/doctors").permitAll()
                .antMatchers("/api/doctors/**").hasRole("DOCTOR")
                .antMatchers("/api/patients/**").hasRole("PATIENT")
            .anyRequest()
                .authenticated()
                .and()
            .addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
