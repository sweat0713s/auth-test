package com.fc.fcauth.config;

import com.fc.fcauth.filter.JwtAuthFilter;
import com.fc.fcauth.repository.EmployeeRepository;
import com.fc.fcauth.service.KakaoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final KakaoService kakaoService;
  private final EmployeeRepository employeeRepository;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  private static final String[] AUTH_ALLOWLIST = {
      "/swagger-ui/**", "/v3/**", "/login/**", "/images/**", "/kakao/**"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.cors(Customizer.withDefaults());

    http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
        SessionCreationPolicy.STATELESS));
    http.formLogin(AbstractHttpConfigurer::disable);

    http.addFilterBefore(new JwtAuthFilter(kakaoService, employeeRepository), UsernamePasswordAuthenticationFilter.class);
    http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers(AUTH_ALLOWLIST).permitAll()
        .anyRequest().authenticated());

    http.exceptionHandling(handler -> handler.authenticationEntryPoint(customAuthenticationEntryPoint));
    return http.build();
  }
}
