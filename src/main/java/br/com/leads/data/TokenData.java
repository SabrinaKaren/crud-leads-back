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
public class TokenData {
    
    private Long userId;
    private String token;
    private long expirationDateInMilliseconds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpirationDateInMilliseconds() {
        return expirationDateInMilliseconds;
    }

    public void setExpirationDateInMilliseconds(long expirationDateInMilliseconds) {
        this.expirationDateInMilliseconds = expirationDateInMilliseconds;
    }
    
}