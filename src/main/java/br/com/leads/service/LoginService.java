/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.service;

import br.com.leads.data.LoginData;
import br.com.leads.data.TokenData;
import br.com.leads.entities.Token;
import br.com.leads.entities.User;
import br.com.leads.repository.TokenRepository;
import br.com.leads.repository.UserRepository;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabrina
 */
@Service
public class LoginService {
    
    @Autowired
    TokenRepository tokenRepository;
    
    @Autowired
    UserRepository userRepository;
    
    public TokenData login(LoginData loginObject) throws Exception {
        
        TokenData objectToReturn = new TokenData();
        
        if (loginObject.getUserName() == null || "".equals(loginObject.getUserName())
                || loginObject.getPassword() == null || "".equals(loginObject.getPassword())){
            throw new Exception("Usuário e senha devem ser preenchidos.");
        }

        Optional<User> user = userRepository.findByUserNameAndPassword(loginObject.getUserName(), loginObject.getPassword());
        if (!user.isPresent()){
            throw new Exception("Usuário e/ou senha inválido(s).");
        }
        
        Token token = new Token();
        Optional<Token> tokenOp = tokenRepository.findByUserId(user.get().getId().intValue());
        if (tokenOp.isPresent()){
            token = tokenOp.get();
        }
        
        // montando a data de validade do token
        Date dateNow = new Date();
        long expirationDateInMilliseconds = dateNow.getTime() + 7200000;
        
        
        token.setToken(UUID.randomUUID().toString());
        token.setExpirationDate(new Timestamp(expirationDateInMilliseconds));
        token = tokenRepository.save(token);
        
        objectToReturn.setUserId(new Long(token.getUserId()));
        objectToReturn.setToken(token.getToken());
        objectToReturn.setExpirationDate(new Date(expirationDateInMilliseconds));
        
        return objectToReturn;
        
    }

}