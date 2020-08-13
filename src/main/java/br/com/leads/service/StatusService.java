/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leads.service;

import br.com.leads.data.StatusData;
import br.com.leads.entities.Statuslead;
import br.com.leads.repository.StatusleadRepository;
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
public class StatusService {
    
    @Autowired
    StatusleadRepository statusRepository;
    
    public List<StatusData> getByNameContains(String nameContains) throws Exception {
        
        List<StatusData> listToReturn = new ArrayList<>();
        
        if (nameContains == null || "".equals(nameContains)){
            throw new Exception("Um nome deve ser informado para que a pesquisa seja realizada.");
        }
        
        List<Statuslead> statusFound = statusRepository.findByDescriptionContainingIgnoreCase(nameContains);
        if (!statusFound.isEmpty()){
            statusFound.forEach((item)->{
                listToReturn.add(new StatusData(item.getId(), item.getDescription()));
            });
        }
        
        return listToReturn;
        
    }
    
    public void save(StatusData statusObject) throws Exception{
        
        if (statusObject.getName() == null || "".equals(statusObject.getName())){
            throw new Exception("O nome do status deve ser informado.");
        }
        
        Statuslead status = new Statuslead();
        if (statusObject.getId() != null){
            Optional<Statuslead> statusOp = statusRepository.findById(statusObject.getId().longValue());
            if (statusOp.isPresent()){
                status = statusOp.get();
            }
        }
        
        status.setDescription(statusObject.getName().toUpperCase());
        statusRepository.save(status);
        
    }
    
    public StatusData getById(Long statusId) throws Exception{
        
        StatusData objectToReturn = new StatusData();
        
        if (statusId == null){
            throw new Exception("O id deve ser informado.");
        }
        
        Optional<Statuslead> statusOp = statusRepository.findById(statusId);
        if (!statusOp.isPresent()){
            throw new Exception("Registro não encontrado!");
        }
        
        objectToReturn.setId(statusOp.get().getId());
        objectToReturn.setName(statusOp.get().getDescription());
        
        return objectToReturn;
        
    }
    
    public void deleteById(Long statusId) throws Exception{
        
        if (statusId == null){
            throw new Exception("O id deve ser informado.");
        }
        
        statusRepository.deleteById(statusId);
        
    }

}