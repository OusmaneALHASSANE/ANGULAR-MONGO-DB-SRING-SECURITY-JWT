package ousmane.alhassane.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   http.csrf().disable();
   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
   http.authorizeRequests().antMatchers("/appUsers/**","/appRoles/**").hasAuthority("ADMIN");
   http.authorizeRequests().antMatchers(HttpMethod.POST,"/clients/**","/analyses/**").hasAuthority("ANALYSE_MANAGER");
   http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/clients/**","/analyses/**").hasAuthority("ANALYSE_MANAGER");
   http.authorizeRequests().antMatchers(HttpMethod.PUT,"/clients/**","/analyses/**").hasAuthority("ANALYSE_MANAGER");
   http.authorizeRequests().anyRequest().authenticated();
   http.addFilter(new JWTAuthentificationFilter(authenticationManager()));
   http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

