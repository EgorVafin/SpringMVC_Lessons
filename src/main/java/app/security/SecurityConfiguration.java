package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/register**")
                .permitAll().anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/login")
                .permitAll()

                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()

//                .and()
//                .rememberMe()
//                .key("secrem")
//                .tokenValiditySeconds(86400)
//                .userDetailsService(userDetailsService);
    ;
    }

}