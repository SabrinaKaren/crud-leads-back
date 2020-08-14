package br.com.leads.repository;

import br.com.leads.entities.Opportunity;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface OpportunityRepository extends PagingAndSortingRepository<Opportunity, Long> {

    public List<Opportunity> findByDescriptionContainingIgnoreCase(String description);
    
    public List<Opportunity> findByLeadId(int leadId);
    
    public List<Opportunity> findByDescriptionContainingIgnoreCaseAndLeadId(String description, int leadId);
    
}