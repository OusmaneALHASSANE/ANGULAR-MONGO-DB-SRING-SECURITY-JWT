package ml.ozzo.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bcpe=getBCPE();
                   auth.inMemoryAuthentication().withUser("admin")
                           .password(bcpe.encode("1234")).roles("ADMIN","USER");
                   auth.inMemoryAuthentication().withUser("user")
                           .password(bcpe.encode("1234")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
  http.csrf().disable();
  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
  http.authorizeRequests().antMatchers("/products/**").hasAuthority("USER");
  http.authorizeRequests().anyRequest().authenticated();
  http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    public BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}




