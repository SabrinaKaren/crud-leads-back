/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sabrina
 */
@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().contains("actuator")) {
            fc.doFilter(request, response);
        }
        
        System.out.println("Authenticating a request from :" + req.getRemoteAddr());
        
        if(req.getHeader("Authorization") != null){
            String token = req.getHeader("Authorization").replace("CustomToken", "").trim();
            try {
                System.out.println("Authenticated a request from :" + req.getRemoteAddr());
                fc.doFilter(request, response);
            } catch (Exception ex) {
                ((HttpServletResponse)response).setStatus(401);
            }
        }else{
            if(req.getRequestURI().equals("/auth/login")){
                fc.doFilter(request, response);
            }else{
                ((HttpServletResponse)response).setStatus(401);
            }
        }
        
    }
    
    @Override
    public void destroy() {
        
    }
    
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authFilter(){
        
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean; 
        
    }
    
}