/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.service;

import br.com.leads.data.LeadData;
import br.com.leads.entities.Lead;
import br.com.leads.repository.LeadRepository;
import br.com.leads.utils.CommonMethods;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabrina
 */
@Service
public class LeadService {
    
    @Autowired
    LeadRepository leadRepository;
    
    @Autowired
    CommonMethods commonMethods;
    
    public LeadData getLeadById(Long leadId) throws Exception {
        
        LeadData objectToReturn = new LeadData();
        
        return objectToReturn;
        
    }
    
    public void save(LeadData leadObject) throws Exception{
        
        if (leadObject.getName() == null || "".equals(leadObject.getName())){
            throw new Exception("O nome deve ser informado.");
        }
        
        Lead lead = new Lead();
        if (leadObject.getId() != null){
            Optional<Lead> leadOp = leadRepository.findById(leadObject.getId());
            if (leadOp.isPresent()){
                lead = leadOp.get();
            }
        }
        
        lead.setDate(new Date());
        lead.setCustomerName(leadObject.getName().toUpperCase());
        lead.setCustomerPhone(commonMethods.getStringNoSpecialCharacters(leadObject.getPhone()));
        lead.setCustomerEmail(leadObject.getEmail());
        lead.setStatusId(leadObject.getStatusId().intValue());
        leadRepository.save(lead);
        
    }

}