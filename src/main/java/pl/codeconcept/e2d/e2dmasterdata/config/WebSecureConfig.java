package pl.codeconcept.e2d.e2dmasterdata.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.codeconcept.e2d.e2dmasterdata.service.jwt.JwtAuthFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Configuration
public class WebSecureConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/student/get/**").authenticated()
                .antMatchers("/school/get/**").authenticated()
                .antMatchers("/school/**").hasRole("ADMIN")
                .antMatchers("/**/add").hasAnyRole("ADMIN", "SCHOOL")
                .antMatchers("/**/delete/**").hasAnyRole("ADMIN", "SCHOOL")
                .antMatchers("/**/update/**").hasAnyRole("ADMIN", "SCHOOL")
                .antMatchers("/**/all").hasAnyRole("ADMIN", "SCHOOL")
                .anyRequest().denyAll()
                .and()
                .addFilter(jwtAuthFilter(authenticationManagerBean()))
                .csrf().disable();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthenticationManager authenticationManager) {
        return new JwtAuthFilter(authenticationManager);
    }
}
