/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.controllers;

import br.com.leads.data.ErrorItemData;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leads.data.EnvelopingResponseData;
import br.com.leads.data.LeadData;
import br.com.leads.service.LeadService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author Sabrina
 */
@RestController
@RequestMapping("/lead")
public class LeadController {

    @Autowired
    LeadService leadService;
    
    @GetMapping("/get-by-name/{name}")
    public EnvelopingResponseData getByNameContains(@RequestHeader("Authorization") String token, @PathVariable String name) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("lead/getByNameContains");
        
        try {
            
            envelopingResponse = envelopingResponse.isSuccess();
            
            List<LeadData> leadsFound = leadService.getByNameContains(name);
            leadsFound.forEach(envelopingResponse.getMsgSaida()::add);
            
        } catch (Exception e) {
            
            envelopingResponse = envelopingResponse.isError();
            
            ErrorItemData error = new ErrorItemData();
            error.setCode("999");
            error.setMessage(e.getMessage());
            
            envelopingResponse.getError().add(error);
            
        }
        
        return envelopingResponse;
        
    }
    
    @GetMapping("/get-by-id/{leadId}")
    public EnvelopingResponseData getLeadById(@RequestHeader("Authorization") String token, @PathVariable Long leadId) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("lead/getLeadById");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            LeadData leadObject = leadService.getLeadById(leadId);
            
            envelopingResponse.getMsgSaida().add(leadObject);
            
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
    public EnvelopingResponseData save(@RequestHeader("Authorization") String token, @RequestBody LeadData leadObject) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("lead/save");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            leadService.save(leadObject);
            
            envelopingResponse.getMsgSaida().add("Lead salvo com sucesso.");
            
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
    
    @DeleteMapping("/delete/{leadId}")
    public EnvelopingResponseData deleteById(@RequestHeader("Authorization") String token, @PathVariable("leadId") Long leadId) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData();
        envelopingResponse.setMethod("lead/deleteById");
        
        try {
            
            envelopingResponse.setResult("SUCCESS");
            envelopingResponse.setMsgSaida(new ArrayList<>());
            
            leadService.deleteById(leadId);
            
            envelopingResponse.getMsgSaida().add("Lead excluído com sucesso.");
            
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