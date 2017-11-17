package org.herba.config;

import org.herba.service.UserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

//必须使用申明此类为安全控制类，否则将会直接调用默认权限配置并且控制台打印默认密码
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    protected UserDetailsService userSecurityService() {
        return new UserSecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //权限名不带ROLE,自动增加前缀
                .antMatchers("/login",  "/css/**","/font/**","/api/**","/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().
                loginPage("/login")
                //设置默认登录成功跳转页面
                .defaultSuccessUrl("/client/home").permitAll()
                .and()
                //开启cookie保存用户数据
                .rememberMe()
                //设置cookie有效期
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .and()
                .logout()
                //默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout")
                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
//                //关闭csrf后可以正常进行常规跳转，无需模板引擎
                .and()
                .csrf().disable();
    }
}
