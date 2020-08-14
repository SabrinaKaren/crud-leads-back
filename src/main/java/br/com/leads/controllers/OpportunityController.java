/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leads.data.EnvelopingResponseData;
import br.com.leads.data.OpportunityData;
import br.com.leads.data.OpportunitySearchData;
import br.com.leads.service.OpportunityService;
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
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    OpportunityService opportunityService;

    @PostMapping("/get-search-object")
    public EnvelopingResponseData getBySearchObject(@RequestHeader("Authorization") String token, @RequestBody OpportunitySearchData opportunitySearchToSearch) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("opportunity/SearchObject");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            List<OpportunityData> opportunitiesFound = opportunityService.getBySearchObject(opportunitySearchToSearch);
            opportunitiesFound.forEach(envelopingResponse.getMsgSaida()::add);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @GetMapping("/get-by-id/{opportunityId}")
    public EnvelopingResponseData getById(@RequestHeader("Authorization") String token, @PathVariable Long opportunityId) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("opportunity/getById");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            OpportunityData opportunityObject = opportunityService.getById(opportunityId);
            envelopingResponse.getMsgSaida().add(opportunityObject);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @PostMapping("/save")
    public EnvelopingResponseData save(@RequestHeader("Authorization") String token, @RequestBody OpportunityData opportunityObject) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("opportunity/save");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            opportunityService.save(opportunityObject);
            envelopingResponse.getMsgSaida().add("Oportunidade salva com sucesso.");
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;	
        
    }
    
    @DeleteMapping("/delete/{opportunityId}")
    public EnvelopingResponseData deleteById(@RequestHeader("Authorization") String token, @PathVariable("opportunityId") Long opportunityId) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("opportunity/deleteById");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            opportunityService.deleteById(opportunityId);
            envelopingResponse.getMsgSaida().add("Oportunidade excluída com sucesso.");
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
		
    }

}