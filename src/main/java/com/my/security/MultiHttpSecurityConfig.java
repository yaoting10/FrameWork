package com.my.security;

import com.my.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
@Configuration
@EnableWebSecurity
public class MultiHttpSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiSecurityConfigurationAdapter extends
            WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.antMatcher("/api/**").csrf().disable();
        }
    }
    @Configuration
    public static class WebsiteUserCenterSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserRepository userRepository;
        @Autowired
        private WebsiteUserDetailService userDetailService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            UserPasswordEncoder encoder = new UserPasswordEncoder();
            provider.setPasswordEncoder(encoder);
            provider.setUserDetailsService(userDetailService);
            auth.authenticationProvider(provider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                        .antMatchers("/user_center/**", "/order/**", "/pay_center/**")
                        .hasRole("USER")
                        .and()
                    .formLogin()
                        .loginPage("/user_center/login")
                        .successHandler(new WebsiteAuthenticationSuccessHandler(userRepository, "/", false, "callBackUrl"))
                        .failureHandler(new WebsiteAuthenticationFailureHandler("/user_center/login?error", "callBackUrl"))
                        .permitAll()
                        .and()
                    .logout()
                        .logoutUrl("/user_center/logout")
                        .logoutSuccessUrl("/")
                        .and()
                    .rememberMe()
                        .key("JIU88-REMEMBERME")
                        .rememberMeServices(new TokenBasedRememberMeServices("JIU88-REMEMBERME", userDetailService))
                        .authenticationSuccessHandler(new RememberMeAuthenticationSuccessHandler(userRepository));
        }
    }
}