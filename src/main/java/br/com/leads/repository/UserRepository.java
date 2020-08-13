package br.com.leads.repository;

import br.com.leads.entities.User;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sabrina
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public Optional<User> findByUserNameAndPassword(String userName, String password);
    
}