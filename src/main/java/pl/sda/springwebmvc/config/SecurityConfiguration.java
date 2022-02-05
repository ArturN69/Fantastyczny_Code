package pl.sda.springwebmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.springwebmvc.service.UserService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/book/admin/**").hasRole("ADMIN")
                .antMatchers("/book/add-th-form").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/book/add").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("robert").password("$2a$12$DxhouXpOTZqu5NSv9h0y0e3NFbKANXFKNmG1z/1D5yeWkIUEIoHRe").roles("USER").and()
//                .withUser("ewa").password("$2a$12$DxhouXpOTZqu5NSv9h0y0e3NFbKANXFKNmG1z/1D5yeWkIUEIoHRe").roles("ADMIN").and()
//                .passwordEncoder(encoder());
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
