package site.rion.PetShop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration
{
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http
			.csrf( (csrf) ->
				csrf.disable()
			)
			.authorizeHttpRequests( (auth) ->
				auth
					.requestMatchers("/admin/**").hasAuthority("ADMIN")
					.requestMatchers("/employee/**").hasAuthority("EMPLOYEE")
					.requestMatchers("/customer/**").hasAuthority("CUSTOMER")
					.requestMatchers("/signup","/css/**","/images/**").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin( (login) ->
				login
					.loginPage("/login")
					.defaultSuccessUrl("/loginSuccess", true)
					.permitAll()
			)
			.logout( (logout) ->
				logout
				.permitAll()
			);
		
		return http.build();
	}
	
	/*
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails user =
				User.builder()
					.username("hossain")
					.password("{noop}rion")
					.roles("USER")
					.build();
		return new InMemoryUserDetailsManager(user);
	}*/
	
	@Bean
	public AuthenticationManager authManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder)
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(authProvider);
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}