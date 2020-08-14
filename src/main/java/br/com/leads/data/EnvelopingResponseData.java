/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sabrina
 */
@Component
public class EnvelopingResponseData {

    private String method;
    private String result;
    private List<Object> msgSaida;
    private List<ErrorItemData> error;

    public EnvelopingResponseData(String method) {
        this.method = method;
    }

    public EnvelopingResponseData() {
    }
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Object> getMsgSaida() {
        return msgSaida;
    }

    public void setMsgSaida(List<Object> msgSaida) {
        this.msgSaida = msgSaida;
    }

    public List<ErrorItemData> getError() {
        return error;
    }

    public void setError(List<ErrorItemData> error) {
        this.error = error;
    }
    
    public EnvelopingResponseData isSuccess(){
        this.setResult("SUCCESS");
        this.setMsgSaida(new ArrayList<>());
        return this;
    }
    
    public EnvelopingResponseData isError(){
        this.setResult("ERROR");
        this.setError(new ArrayList<>());
        ErrorItemData error = new ErrorItemData();
        this.getError().add(error.enveloping());
        return this;
    }
    
}