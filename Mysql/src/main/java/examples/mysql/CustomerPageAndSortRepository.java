package examples.mysql;

import org.springframework.data.repository.PagingAndSortingRepository;;
public interface CustomerPageAndSortRepository extends PagingAndSortingRepository<Customer,Long>{

}
