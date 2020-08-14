/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.data;

/**
 *
 * @author Sabrina
 */
public class ErrorItemData {
    
    private String code;
    private String message;

    public ErrorItemData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ErrorItemData enveloping(){
        this.setCode("999");
        this.setMessage("Não foi possível seguir com a solicitação.");
        return this;
    }
    
}