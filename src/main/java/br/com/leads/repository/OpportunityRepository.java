package br.com.leads.repository;

import br.com.leads.entities.Opportunity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface OpportunityRepository extends PagingAndSortingRepository<Opportunity, Long> {

    
    
}