package org.herba.security;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.herba.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//必须使用申明此类为安全控制类，否则将会直接调用默认权限配置并且控制台打印默认密码
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    protected UserDetailsService userSecurityService() {
        return new UserSecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Autowired
    protected AuthenticationSuccessHandler formAuthenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler formAuthenticationFailureHandler;
    @Autowired
    protected LogoutSuccessHandler formLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //权限名不带ROLE,自动增加前缀
                .antMatchers("/css/**", "/font/**", "/admin/login", "/admin/post/**", "/admin/page/**", "/admin/mid/**", "/admin/tags", "/admin/categorys", "/admin/category/**", "/getImage/**", "/getFile/**","/admin/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/admin/login")
                .successHandler(formAuthenticationSuccessHandler)
                .failureHandler(formAuthenticationFailureHandler)
                .and()
                .logout()
                //默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/admin/logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .logoutSuccessHandler(formLogoutSuccessHandler)
                //关闭csr然后开启cors
                .and()
                .csrf().disable().cors();
    }
}
