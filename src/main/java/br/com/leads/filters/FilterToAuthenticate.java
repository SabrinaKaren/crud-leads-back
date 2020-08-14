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
public class FilterToAuthenticate implements Filter {
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        
        if(req.getHeader("Authorization") != null){
            try {
                fc.doFilter(request, response);
            } catch (Exception e) {
                ((HttpServletResponse)response).setStatus(401);
            }
        }else{
            // se for endpoint de login, não precisa ter token
            if(req.getRequestURI().equals("/auth/login") || req.getRequestURI().equals("/auth/new-user")){
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
    public FilterRegistrationBean<FilterToAuthenticate> authFilter(){
        
        FilterRegistrationBean<FilterToAuthenticate> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FilterToAuthenticate());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean; 
        
    }
    
}