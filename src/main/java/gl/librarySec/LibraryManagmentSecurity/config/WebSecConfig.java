package gl.librarySec.LibraryManagmentSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new DomainUserDetailsService();
    }
    @Bean
    public PasswordEncoder getBcrBCryptEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(getUserDetailsService());
        auth.setPasswordEncoder(getBcrBCryptEncoder());
        return auth;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.authenticationProvider(getDaoAuthenticationProvider());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/put/page/*", "/user/put/post/*", "/user", "/user/", "/user/get/*").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/add/page/", "/user/add/post/", "/user/del/page/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").successForwardUrl("/user").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/user/403")
                .and()
                .cors().and().csrf().disable();
    }
}