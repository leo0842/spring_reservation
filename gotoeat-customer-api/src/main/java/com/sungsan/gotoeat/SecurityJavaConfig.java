package com.sungsan.gotoeat;

import com.sungsan.gotoeat.filters.JwtAuthenticationFilter;
import com.sungsan.gotoeat.utils.JwtUtil;
import javax.servlet.Filter;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

  @Value("${jwt.secret}")
  private String secretKey;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());
    http
        .csrf().disable()
        .cors().disable()
        .formLogin().disable()
        .headers().frameOptions().disable()
        .and()
        .addFilter(filter)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtUtil jwtUtil() {
    return new JwtUtil(secretKey);
  }

}
