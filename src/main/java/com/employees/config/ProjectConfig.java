 package com.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.MvcMatchersAuthorizedUrl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.employees.service.impl.StudentLoginService;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter  {

	@Bean
	public UserDetailsService userDetailsService()
	{
		
		return new StudentLoginService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override 
	protected void configure( HttpSecurity http) throws Exception
	{
		http.httpBasic();
		http.csrf().disable();
		http.authorizeRequests()
			.mvcMatchers("/employees").authenticated()
			.mvcMatchers("/student").permitAll();
	}
}
 

