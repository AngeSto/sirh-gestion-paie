package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private PasswordEncoder passwordEncoder; 
	@Autowired private DataSource dataSource; 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication() 
	.dataSource(dataSource) 
	.passwordEncoder(passwordEncoder) 
	.usersByUsernameQuery("select NOMUTILISATEUR, MOTDEPASSE, ESTACTIF from UTILISATEUR where NOMUTILISATEUR=?") 
	.authoritiesByUsernameQuery("select NOMUTILISATEUR,ROLE from UTILISATEUR where NOMUTILISATEUR = ?"); 
	}
	
	//si authentifie en dur
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/mvc/connexion", "/bootstrap/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/mvc/connexion");
	}
}