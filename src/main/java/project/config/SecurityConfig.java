package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import project.security.MySavedRequestAwareAuthenticationSuccessHandler;
import project.security.RestAuthenticationEntryPoint;

/**
 * Configuration class for Spring Security
 */
@Configuration
@EnableWebSecurity
@ComponentScan("project.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * Configure user and role searching in dataBase
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Configure base http security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .successHandler(mySuccessHandler())
                .failureHandler(myFailureHandler())
                .and()
                .logout();
    }

    /**
     * Bean for SuccessHandler
     */
    @Bean
    public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
        return new MySavedRequestAwareAuthenticationSuccessHandler();
    }

    /**
     * Bean for failure handler
     */
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    /**
     * Bean for authentication provider, need for authentication manager builder
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    /**
     * Bean for BCrypt encoder. need to encode passwords
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    /**
     * Native SQL methods for searching users and roles in dataBase
     */
    /*
    private String getUserQuery() {
        return "SELECT user_name as username, user_password as password "
                + "FROM users "
                + "WHERE user_name = ?";
    }
    private String getAuthoritiesQuery() {
        return "SELECT DISTINCT users.user_name as username, roles.role_name as authority "
                + "FROM users, users_roles, roles "
                + "WHERE users.user_id = users_roles.fk_users "
                + "AND roles.role_id = users_roles.fk_roles "
                + "AND users.user_name = ?";
    }
    */
}