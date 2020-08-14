/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.utils;

import br.com.leads.entities.Statuslead;
import br.com.leads.repository.StatusleadRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sabrina
 */
@Component
public class CommonMethods {
    
    @Autowired
    StatusleadRepository statusleadRepository;
    
    public String getStringNoSpecialCharacters(String expression){
        
        if (expression != null && !"".equals(expression)){
            expression = expression.replace("-", "");
            expression = expression.replace(".", "");
            expression = expression.replace(" ", "");
            expression = expression.replace("/", "");
            expression = expression.replace("\"", "");
            expression = expression.replace("(", "");
            expression = expression.replace(")", "");
        }

        return expression;

    }
    
    public String getStatusNameByStatusId(Long statusId){
        
        Optional<Statuslead> statusOp = statusleadRepository.findById(statusId);
        if (statusOp.isPresent()){
            return statusOp.get().getDescription();
        }
        
        return null;
        
    }
    
}