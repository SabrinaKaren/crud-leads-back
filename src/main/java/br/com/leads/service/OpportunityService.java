/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.service;

import br.com.leads.data.OpportunityData;
import br.com.leads.data.OpportunitySearchData;
import br.com.leads.entities.Opportunity;
import br.com.leads.repository.LeadRepository;
import br.com.leads.repository.OpportunityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabrina
 */
@Service
public class OpportunityService {
    
    @Autowired
    LeadRepository leadRepository;
    
    @Autowired
    OpportunityRepository opportunityRepository;
    
    public List<OpportunityData> getBySearchObject(OpportunitySearchData opportunitySearchToSearch) throws Exception {
        
        List<OpportunityData> listToReturn = new ArrayList<>();
        
        String descriptionContain = "";
        if (opportunitySearchToSearch.getDescriptionContain() != null && !"".equals(opportunitySearchToSearch.getDescriptionContain())){
            descriptionContain = opportunitySearchToSearch.getDescriptionContain();
        }
        
        if ("".equals(descriptionContain) && opportunitySearchToSearch.getLeadId() == null){
            throw new Exception("Pelo menos um dos campos de pesquisa deve ser preenchido.");
        }
        
        List<Opportunity> opportunitiesFound = new ArrayList<>();
        if (!"".equals(descriptionContain) && opportunitySearchToSearch.getLeadId() != null){
            opportunitiesFound = opportunityRepository.findByDescriptionContainingIgnoreCaseAndLeadId(
                    descriptionContain,
                    opportunitySearchToSearch.getLeadId().intValue()
            );
        } else if (!"".equals(descriptionContain) && opportunitySearchToSearch.getLeadId() == null) {
            opportunitiesFound = opportunityRepository.findByDescriptionContainingIgnoreCase(descriptionContain);
        } else if ("".equals(descriptionContain) && opportunitySearchToSearch.getLeadId() != null){
            opportunitiesFound = opportunityRepository.findByLeadId(
                    opportunitySearchToSearch.getLeadId().intValue()
            );
        }
        
        if (!opportunitiesFound.isEmpty()){
            opportunitiesFound.forEach((item)->{
                listToReturn.add(this.buildOpportunityData(item));
            });
        }
        
        return listToReturn;
        
    }
    
    public OpportunityData getById(Long opportunityId) throws Exception{
        
        OpportunityData objectToReturn = new OpportunityData();
        
        if (opportunityId == null){
            throw new Exception("O id deve ser informado.");
        }
        
        Optional<Opportunity> opportunityOp = opportunityRepository.findById(opportunityId);
        if (!opportunityOp.isPresent()){
            throw new Exception("Registro não encontrado!");
        }

        objectToReturn.setId(opportunityOp.get().getId());
        objectToReturn.setLeadId(new Long(opportunityOp.get().getLeadId()));
        objectToReturn.setDescription(opportunityOp.get().getDescription());
        
        return objectToReturn;
        
    }
    
    public void save(OpportunityData opportunityObject) throws Exception{
        
        if (opportunityObject.getDescription() == null || "".equals(opportunityObject.getDescription())
                || opportunityObject.getLeadId() == null){
            throw new Exception("Preencha todos os campos obrigatórios.");
        }
        
        Opportunity opportunity = new Opportunity();
        if (opportunityObject.getId() != null){
            Optional<Opportunity> opportunityOp = opportunityRepository.findById(opportunityObject.getId());
            if (opportunityOp.isPresent()){
                opportunity = opportunityOp.get();
            }
        }
        
        opportunity.setDescription(opportunityObject.getDescription());
        opportunity.setLeadId(opportunityObject.getLeadId().intValue());
        opportunityRepository.save(opportunity);
        
    }
    
    public void deleteById(Long opportunityId) throws Exception{
        
        if (opportunityId == null){
            throw new Exception("O id deve ser informado.");
        }
        
        opportunityRepository.deleteById(opportunityId);
        
    }
    
    public OpportunityData buildOpportunityData(Opportunity opportunity) {
        
        OpportunityData objectToReturn = new OpportunityData();
        
        objectToReturn.setId(opportunity.getId());
        objectToReturn.setLeadId(new Long(opportunity.getLeadId()));
        objectToReturn.setDescription(opportunity.getDescription());
        
        return objectToReturn;
        
    }

}