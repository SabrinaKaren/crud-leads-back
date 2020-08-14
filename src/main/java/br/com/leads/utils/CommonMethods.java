/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.utils;

import org.springframework.stereotype.Component;

/**
 *
 * @author Sabrina
 */
@Component
public class CommonMethods {
    
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
    
}