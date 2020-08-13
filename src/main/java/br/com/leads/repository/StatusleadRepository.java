package br.com.leads.repository;

import br.com.leads.entities.Statuslead;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface StatusleadRepository extends PagingAndSortingRepository<Statuslead, Long> {

    public List<Statuslead> findByDescriptionContainingIgnoreCase(String description);
    
}