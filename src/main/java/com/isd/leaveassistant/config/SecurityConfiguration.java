package com.isd.leaveassistant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    private final String USER_QUERY = "select email, password, active from user where email=?";
    private final String ROLE_QUERY = "select email, 'ROLE_USER' from user where email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(ROLE_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin().loginPage("/login").failureUrl("/login/error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");
    }
}
