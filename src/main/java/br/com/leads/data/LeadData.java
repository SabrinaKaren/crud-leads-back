/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.data;

import java.util.Date;

/**
 *
 * @author Sabrina
 */
public class LeadData {
    
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long statusId;
    private String statusName;
    private Date lastUpdateDate;

    public LeadData(Long id, String name, String phone, String email, Long statusId, String statusName, Date lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.statusId = statusId;
        this.statusName = statusName;
        this.lastUpdateDate = lastUpdateDate;
    }

    public LeadData(Long id) {
        this.id = id;
    }

    public LeadData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
}