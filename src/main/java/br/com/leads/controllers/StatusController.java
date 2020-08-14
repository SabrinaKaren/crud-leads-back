/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.controllers;

import br.com.leads.data.ErrorItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leads.data.EnvelopingResponseData;
import br.com.leads.data.StatusData;
import br.com.leads.service.StatusService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author Sabrina
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping("/get-by-name/{name}")
    public EnvelopingResponseData getByNameContains(@RequestHeader("Authorization") String token, @PathVariable String name) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("status/getByNameContains");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            List<StatusData> statusFound = statusService.getByNameContains(name);
            statusFound.forEach((item)->{
                envelopingResponse.getMsgSaida().add(item);
            });
            
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
    
    @GetMapping("/get-by-id/{statusId}")
    public EnvelopingResponseData getById(@RequestHeader("Authorization") String token, @PathVariable Long statusId) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("status/getById");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            StatusData statusObject = statusService.getById(statusId);
            
            envelopingResponse.getMsgSaida().add(statusObject);
            
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
    
    @PostMapping("/save")
    public EnvelopingResponseData save(@RequestHeader("Authorization") String token, @RequestBody StatusData statusObject) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("status/save");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            statusService.save(statusObject);
            
            envelopingResponse.getMsgSaida().add("Satus salvo com sucesso.");
            
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
    
    @DeleteMapping("/delete/{statusId}")
    public EnvelopingResponseData deleteById(@RequestHeader("Authorization") String token, @PathVariable("statusId") Long statusId) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("status/deleteById");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            statusService.deleteById(statusId);
            
            envelopingResponse.getMsgSaida().add("Status excluído com sucesso.");
            
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
    
    @GetMapping("/get-all")
    public EnvelopingResponseData getAll(@RequestHeader("Authorization") String token) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("status/getAll");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            List<StatusData> statusList = statusService.getAll();
            statusList.forEach((item)->{
                envelopingResponse.getMsgSaida().add(item);
            });
            
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