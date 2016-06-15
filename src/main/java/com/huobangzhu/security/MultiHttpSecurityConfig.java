package com.huobangzhu.security;

import com.huobangzhu.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
        private UserService userService;
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
                        .antMatchers("/index/**")
                        .hasAnyRole("ADMIN", "USER")
                        .antMatchers( "/wayBill", "/wayBill/list","/wayBill/edit")
                        .hasAnyRole("ADMIN", "USER")
                        .antMatchers( "/wayBill/**")
                        .hasAnyRole("ADMIN")
                        .antMatchers("/user/my")
                        .hasAnyRole("ADMIN", "USER")
                        .antMatchers("/user/**")
                        .hasAnyRole("ADMIN")
                        .antMatchers("/handlingCost/**")
                        .hasAnyRole("ADMIN")
                        .and()
                    .formLogin()
                        .loginPage("/")
                        .successHandler(new WebsiteAuthenticationSuccessHandler(userService, "/index", false, "callBackUrl"))
                        .permitAll()
                        .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index");
        }
    }
}
