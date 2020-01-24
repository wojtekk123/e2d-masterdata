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

    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthenticationManager authenticationManager) {
        return new JwtAuthFilter(authenticationManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/car/get/**").permitAll()
                .antMatchers("/car/**").hasAnyRole("ADMIN","SCHOOL")
                .antMatchers("/school/get/**").authenticated()
                .antMatchers("/school/all").authenticated()
                .antMatchers("/school/update/**").hasAnyRole("SCHOOL","ADMIN")
                .antMatchers("/school/**").hasRole("ADMIN")
                .antMatchers("/student/get/**").authenticated()
                .antMatchers("/student/add").hasAnyRole("SCHOOL","ADMIN")
                .antMatchers("/student/all").authenticated()
                .antMatchers("/student/get/**").authenticated()
                .antMatchers("/instructor/**").hasAnyRole("SCHOOL","ADMIN","INSTRUCTOR")
                .antMatchers("/instructor/all").authenticated()
                .antMatchers("/ride/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilter(jwtAuthFilter(authenticationManagerBean()))
                .csrf().disable();
    }
}

