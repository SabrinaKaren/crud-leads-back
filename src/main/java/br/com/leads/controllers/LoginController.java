/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.controllers;

import br.com.leads.data.ErrorItemData;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leads.data.EnvelopingResponseData;
import br.com.leads.data.LoginData;
import br.com.leads.data.TokenData;
import br.com.leads.service.LoginService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Sabrina
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    LoginService loginService;
    
    @PostMapping("/login")
    public EnvelopingResponseData login(@RequestBody LoginData loginObject) {
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("login");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            TokenData tokenObject = loginService.login(loginObject);
            if (tokenObject == null){
                envelopingResponse.getMsgSaida().add("Usuário e/ou senha inválido(s).");
            } else {
                envelopingResponse.getMsgSaida().add(tokenObject);
            }            
            
        } catch (Exception e) {
            
            envelopingResponse.setResult("ERROR");
            envelopingResponse.setError(new ArrayList<>());
            
            ErrorItemData error = new ErrorItemData();
            error.setCode("999");
            error.setMessage(e.getMessage());
            
            envelopingResponse.getError().add(error);
            
        }
        
        return envelopingResponse;
        
    }

}