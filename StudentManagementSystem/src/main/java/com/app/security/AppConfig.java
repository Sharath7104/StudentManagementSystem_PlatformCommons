package com.app.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {
	

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		.cors(cors ->{
			
			
			cors.configurationSource(new CorsConfigurationSource() {
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					
				CorsConfiguration cfg= new CorsConfiguration();
				
				
				cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				return cfg;				
					
					
					
				}
			});
			
			
		})
		.authorizeHttpRequests(auth ->{
			auth
				.requestMatchers(HttpMethod.POST,"/signIn").permitAll()
//				.requestMatchers("/students/**","/admins/**", "/courses/**","/signIn").permitAll()
				.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
				.requestMatchers("/students/**","/admin/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.GET, "/customers/**").hasAnyRole("ADMIN","STUDENT")
				.anyRequest().authenticated();
			
				})
			.csrf(csrf -> csrf.disable())
			.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		
		
		return http.build();

	}
	
	@Bean
	  public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components().addSecuritySchemes("bearer-jwt",
	                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
	                    .in(SecurityScheme.In.HEADER).name("Authorization")))
	            .info(new Info().title("Student Management System API").version("snapshot").description(
						"This is Student Management System app REST API using spring boot. \n I have used spring data jpa data operations and spring-security using JWT for user validation / authentication.")
						.termsOfService("8080/swagger-ui/index.html"))
	            .addSecurityItem(
	                    new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
	  }

	
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	

}

