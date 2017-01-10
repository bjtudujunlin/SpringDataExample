package examples.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {
	@Autowired
	private CustomerCURDRepository repository;
	
	@RequestMapping("/create")
	  @ResponseBody
	  public String create(String firstName, String lastName) {
	    String result = "";
	    try {
	      Customer customer = new Customer(firstName, lastName);
	      result = repository.save(customer).toString();
	    }
	    catch (Exception ex) {
	      return "Error creating the customer: " + ex.toString();
	    }
	    return "User succesfully created " + result;
	  }
}
