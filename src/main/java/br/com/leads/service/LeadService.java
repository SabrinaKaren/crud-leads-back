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
import br.com.leads.utils.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    @Autowired
    Constants constants;
    
    public List<LeadData> getByNameContains(String nameContains) throws Exception {
        
        List<LeadData> listToReturn = new ArrayList<>();
        
        if (nameContains == null || "".equals(nameContains)){
            throw new Exception("Um nome deve ser informado para que a pesquisa seja realizada.");
        }
        
        List<Lead> leadsFound = leadRepository.findByCustomerNameContainingIgnoreCase(nameContains);
        if (!leadsFound.isEmpty()){
            leadsFound.forEach((item)->{
                listToReturn.add(new LeadData(
                        item.getId(),
                        item.getCustomerName(),
                        item.getCustomerPhone(),
                        item.getCustomerEmail(),
                        item.getStatusId() != null ? new Long(item.getStatusId()) : null,
                        item.getStatusId() != null ? commonMethods.getStatusNameByStatusId(new Long(item.getStatusId())) : null,
                        item.getDate()
                ));
            });
        }
        
        return listToReturn;
        
    }
    
    public LeadData getLeadById(Long leadId) throws Exception {
        
        LeadData objectToReturn = new LeadData();
        
        if (leadId == null){
            throw new Exception(constants.idIsNecessary);
        }
        
        Optional<Lead> leadOp = leadRepository.findById(leadId);
        if (!leadOp.isPresent()){
            throw new Exception(constants.registerNotFound);
        }
        
        objectToReturn.setId(leadOp.get().getId());
        objectToReturn.setName(leadOp.get().getCustomerName());
        objectToReturn.setPhone(leadOp.get().getCustomerPhone());
        objectToReturn.setEmail(leadOp.get().getCustomerEmail());
        if (leadOp.get().getStatusId() != null){
            objectToReturn.setStatusId(new Long(leadOp.get().getStatusId()));
        }
        objectToReturn.setLastUpdateDate(leadOp.get().getDate());
        
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
        if (leadObject.getStatusId() != null){
            lead.setStatusId(leadObject.getStatusId().intValue());
        }
        leadRepository.save(lead);
        
    }
    
    public void deleteById(Long leadId) throws Exception{
        
        if (leadId == null){
            throw new Exception("O id deve ser informado.");
        }
        
        leadRepository.deleteById(leadId);
        
    }

}