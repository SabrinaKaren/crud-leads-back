/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.data;

/**
 *
 * @author Sabrina
 */
public class OpportunitySearchData {
    
    private Long leadId;
    private String descriptionContain;

    public OpportunitySearchData() {
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public String getDescriptionContain() {
        return descriptionContain;
    }

    public void setDescriptionContain(String descriptionContain) {
        this.descriptionContain = descriptionContain;
    }
    
}