/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sabrina
 */
@Entity
@Table(name = "Statuslead")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statuslead.findAll", query = "SELECT s FROM Statuslead s")
    , @NamedQuery(name = "Statuslead.findById", query = "SELECT s FROM Statuslead s WHERE s.id = :id")
    , @NamedQuery(name = "Statuslead.findByDescription", query = "SELECT s FROM Statuslead s WHERE s.description = :description")})
public class Statuslead implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    
    @Size(max = 120)
    private String description;

    public Statuslead() {
    }

    public Statuslead(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Statuslead)) {
            return false;
        }
        Statuslead other = (Statuslead) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.leads.entities.Statuslead[ id=" + id + " ]";
    }
    
}
