package com.eray.authservice.config;

import com.eray.authservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Order(2147483636)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(DataSource dataSource,CustomUserDetailsService userDetailsService) {
        this.dataSource=dataSource;
        this.userDetailsService=userDetailsService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic().disable();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String encoded = new BCryptPasswordEncoder().encode("password");

        auth.inMemoryAuthentication()
                .withUser("user").password(encoded).roles("USER")
                .and()
                .withUser("admin").password(encoded).roles("ADMIN")
                .and().passwordEncoder(encoder());
//            auth.parentAuthenticationManager(authenticationManager);
    }*/

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .and()
                .jdbcAuthentication()
                .passwordEncoder(encoder())
                .dataSource(dataSource);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}
