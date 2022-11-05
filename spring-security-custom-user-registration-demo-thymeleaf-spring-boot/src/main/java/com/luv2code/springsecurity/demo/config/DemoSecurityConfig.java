package com.luv2code.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.luv2code.springsecurity.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our user service
    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE") // everyone will have access to home page
			.antMatchers("/leaders/**").hasRole("MANAGER") // only users with MANAGER role will be able to see /leaders/** pages
			.antMatchers("/systems/**").hasRole("ADMIN") // only users with ADMIN role will be able to see /systems/** pages
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage") // redirect to our login page => fancy-login.html
				.loginProcessingUrl("/authenticateTheUser") // logic that will process login form
				.successHandler(customAuthenticationSuccessHandler) // class that will redirect user to home page if login attempt was successfull
				.permitAll()
			.and()
			.logout().permitAll() // everyone can logout
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied"); // redirect user if he tries to access unauthorized page
		
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}






