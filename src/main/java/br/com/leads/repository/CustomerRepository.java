package br.com.leads.repository;

import br.com.leads.entities.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    
    
}