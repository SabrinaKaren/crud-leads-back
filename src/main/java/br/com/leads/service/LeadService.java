/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.service;

import br.com.leads.data.LeadData;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabrina
 */
@Service
public class LeadService {
    
    public LeadData getLeadById(Long leadId) throws Exception {
        
        LeadData objectToReturn = new LeadData();
        
        return objectToReturn;
        
    }

}