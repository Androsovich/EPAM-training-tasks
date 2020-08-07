package com.epam.winter.java.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Import(SpringJdbcConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("werty").password("werty").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("qqqq").password("qqqq").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                .formLogin().loginPage("/login.html").permitAll().usernameParameter("j_username")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/login-error.html")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/security/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable();
    }
}
