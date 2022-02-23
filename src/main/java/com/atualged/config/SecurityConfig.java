package com.atualged.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(
		  prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService user;
	
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
     //   auth.inMemoryAuthentication()
     //     .withUser("robert").password(encoder.encode("robert")).roles("USER");
		auth.userDetailsService(user).passwordEncoder(encoder);
    }
	

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() 
      throws Exception {
        return super.authenticationManagerBean();
    }  
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        String o[] = {"*"};
        configuration.setAllowedOrigins(Arrays.asList(o));  //set access from all domains
        String a[] = {"GET", "POST", "PUT", "DELETE"};
        configuration.setAllowedMethods(Arrays.asList(a));
        configuration.setAllowCredentials(true);
        String c[] = {"Authorization", "Cache-Control", "Content-Type"};
        configuration.setAllowedHeaders(Arrays.asList(c));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
   
}