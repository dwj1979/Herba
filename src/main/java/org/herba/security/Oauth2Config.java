package org.herba.security;

import org.herba.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Oauth2Config {
    private static String resourceId = "herbaResource";

    @Configuration
    @EnableResourceServer
    public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                    .resourceId(resourceId)
                    .tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/oauth/token","/css/**", "/font/**", "/admin/login", "/admin/post/**",
                            "/admin/page/**", "/admin/mid/**", "/admin/tags", "/admin/categorys",
                            "/admin/category/**", "/getImage/**", "/getFile/**","/admin/register","/admin/comments","/oauth/token").permitAll()
                    .anyRequest().authenticated();
            ;
        }
    }

    @Configuration
    @EnableAuthorizationServer
    public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        UserSecurityService userSecurityService;
        @Autowired
        private TokenStore tokenStore;
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        @Autowired
        private WebResponseExceptionTranslator loggingExceptionTranslator;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore)
                    .accessTokenConverter(jwtAccessTokenConverter)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userSecurityService)
                    .exceptionTranslator(loggingExceptionTranslator);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("herba")
                    .authorizedGrantTypes("password", "refresh_token")
                    .authorities("ADMIN", "USER")
                    .scopes("read", "write")
                    .secret("herba")
                    .resourceIds(resourceId);
        }
    }
}