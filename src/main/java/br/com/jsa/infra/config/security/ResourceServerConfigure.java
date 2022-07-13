package br.com.jsa.infra.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allow method annotations like @PreAuthorize
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
		.authorizeRequests()
	        .antMatchers("/swagger**").permitAll()
	        .antMatchers("/springfox**").permitAll()
	        .antMatchers("/v2/api-docs").permitAll()
	        .antMatchers("/webjars/**").permitAll()
	        .antMatchers("/configuration/**").permitAll()
	        .antMatchers("/swagger-resources/**").permitAll()
			.anyRequest().authenticated();
	}
	

}
