package br.com.leads.repository;

import br.com.leads.entities.Lead;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface LeadRepository extends PagingAndSortingRepository<Lead, Long> {

    public List<Lead> findByCustomerNameContainingIgnoreCase(String customerName);
    
    @Override
    public List<Lead> findAll();
    
}