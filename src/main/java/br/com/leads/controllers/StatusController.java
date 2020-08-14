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
import br.com.leads.data.StatusData;
import br.com.leads.service.StatusService;
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
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("status/getByNameContains");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            List<StatusData> statusFound = statusService.getByNameContains(name);
            statusFound.forEach(envelopingResponse.getMsgSaida()::add);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @GetMapping("/get-by-id/{statusId}")
    public EnvelopingResponseData getById(@RequestHeader("Authorization") String token, @PathVariable Long statusId) throws Exception{
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("status/getById");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            StatusData statusObject = statusService.getById(statusId);
            envelopingResponse.getMsgSaida().add(statusObject);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }
    
    @PostMapping("/save")
    public EnvelopingResponseData save(@RequestHeader("Authorization") String token, @RequestBody StatusData statusObject) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("status/save");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            statusService.save(statusObject);
            envelopingResponse.getMsgSaida().add("Satus salvo com sucesso.");
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;	
        
    }
    
    @DeleteMapping("/delete/{statusId}")
    public EnvelopingResponseData deleteById(@RequestHeader("Authorization") String token, @PathVariable("statusId") Long statusId) {

    	EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("status/deleteById");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            statusService.deleteById(statusId);
            envelopingResponse.getMsgSaida().add("Status excluído com sucesso.");
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
        
        EnvelopingResponseData envelopingResponse = new EnvelopingResponseData("status/getAll");
        
        try {
            envelopingResponse = envelopingResponse.isSuccess();
            List<StatusData> statusList = statusService.getAll();
            statusList.forEach(envelopingResponse.getMsgSaida()::add);
        } catch (Exception e) {
            envelopingResponse = envelopingResponse.isError();
            if (e.getMessage() != null && !"".equals(e.getMessage())){
                envelopingResponse.getError().get(0).setMessage(e.getMessage());
            }
        }
        
        return envelopingResponse;
        
    }

}