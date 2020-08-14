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
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @GetMapping("/get-by-id/{leadId}")
    public EnvelopingResponseData getLeadById(@RequestHeader("Authorization") String token, @PathVariable Long leadId) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("lead/getLeadById");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            LeadData leadObject = leadService.getLeadById(leadId);
            envelopingResponse.getMsgSaida().add(leadObject);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @PostMapping("/save")
    public EnvelopingResponseData save(@RequestHeader("Authorization") String token, @RequestBody LeadData leadObject) {
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("lead/save");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            leadService.save(leadObject);
            envelopingResponse.getMsgSaida().add("Lead salvo com sucesso.");
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @DeleteMapping("/delete/{leadId}")
    public EnvelopingResponseData deleteById(@RequestHeader("Authorization") String token, @PathVariable("leadId") Long leadId) {
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("lead/deleteById");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            leadService.deleteById(leadId);
            envelopingResponse.getMsgSaida().add("Lead excluído com sucesso.");
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
		
    }
    
    @GetMapping("/get-all")
    public EnvelopingResponseData getAll(@RequestHeader("Authorization") String token) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("lead/getAll");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            List<LeadData> leadsList = leadService.getAll();
            leadsList.forEach(envelopingResponse.getMsgSaida()::add);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }

}