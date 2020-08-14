/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leads.data.EnvelopingResponseData;
import br.com.leads.data.LoginData;
import br.com.leads.data.TokenData;
import br.com.leads.service.LoginService;
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
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("login/login");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            TokenData tokenObject = loginService.login(loginObject);
            envelopingResponse.getMsgSaida().add(tokenObject);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }

}