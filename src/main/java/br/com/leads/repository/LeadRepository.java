package br.com.leads.repository;

import br.com.leads.entities.Lead;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface LeadRepository extends PagingAndSortingRepository<Lead, Long> {

    
    
}