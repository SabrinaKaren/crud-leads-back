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
public class StatusData {
    
    private Long id;
    private String name;
    
    public StatusData() {
    }
    
    public StatusData(String name) {
        this.name = name;
    }

    public StatusData(Long id, String name) {
        this.id = id;
        this.name = name;
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
    
}