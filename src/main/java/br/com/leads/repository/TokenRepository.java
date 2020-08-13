package br.com.leads.repository;

import br.com.leads.entities.Token;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {

    public Optional<Token> findByUserId(int userId);
    
}