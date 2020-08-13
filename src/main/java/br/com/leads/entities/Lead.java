/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sabrina
 */
@Entity
@Table(name = "Lead")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lead.findAll", query = "SELECT l FROM Lead l")
    , @NamedQuery(name = "Lead.findById", query = "SELECT l FROM Lead l WHERE l.id = :id")
    , @NamedQuery(name = "Lead.findByDate", query = "SELECT l FROM Lead l WHERE l.date = :date")
    , @NamedQuery(name = "Lead.findByCustomerName", query = "SELECT l FROM Lead l WHERE l.customerName = :customerName")
    , @NamedQuery(name = "Lead.findByCustomerPhone", query = "SELECT l FROM Lead l WHERE l.customerPhone = :customerPhone")
    , @NamedQuery(name = "Lead.findByCustomerEmail", query = "SELECT l FROM Lead l WHERE l.customerEmail = :customerEmail")
    , @NamedQuery(name = "Lead.findByStatusId", query = "SELECT l FROM Lead l WHERE l.statusId = :statusId")})
public class Lead implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String customerName;
    
    @Size(max = 12)
    private String customerPhone;
    
    @Size(max = 255)
    private String customerEmail;
    
    private Integer statusId;

    public Lead() {
    }

    public Lead(Long id) {
        this.id = id;
    }

    public Lead(Long id, Date date, String customerName) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lead)) {
            return false;
        }
        Lead other = (Lead) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.leads.entities.Lead[ id=" + id + " ]";
    }
    
}
