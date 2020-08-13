/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Sabrina
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        long startTime = System.currentTimeMillis();
        System.out.println("\n-------- LogInterception.preHandle --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Start Time: " + System.currentTimeMillis());
 
        request.setAttribute("startTime", startTime);
 
        return true;
        
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("\n-------- LogInterception.postHandle --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Processing End Time: " + System.currentTimeMillis());
    }
    
    @Override
    public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex) 
      throws Exception {

        System.out.println("\n-------- LogInterception.afterCompletion --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("End Time: " + System.currentTimeMillis());
        
    }
    
}