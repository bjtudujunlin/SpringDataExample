package examples.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCURDRepository extends CrudRepository<Customer, Long> {
	
    List<Customer> findByLastName(String lastName);
    
    List<Customer> findByFirstName(String firstName); 
    
    @Query("select a from Customer a WHERE a.firstName = ?1")
    List<Customer> findByQuery(String firstName);
}