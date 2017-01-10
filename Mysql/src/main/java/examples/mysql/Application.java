package examples.mysql;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	/**
	 * 返回CommandLineRunner的Bean，在spring boot启动前加载并且执行
	 * 
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(CustomerCURDRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// 检索所有顾客
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// 根据ID检索
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// 根据lastname检索
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}

			// 根据first检索
			log.info("Customer found with findByFirstName('David'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByFirstName("David")) {
				log.info(bauer.toString());
			}
			log.info("");
			
			// 自定义查询
			log.info("Customer found with findByQuery('Kim'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByQuery("Kim")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
	
	@Bean
	public CommandLineRunner demoPageAndSort(CustomerPageAndSortRepository repository) {
		return (args) -> {
			// 排序-支持多属性排序，Sort构造是利用list<Order>作为参数
			log.info("Customer found with sort by id asc");
			log.info("--------------------------------------------");
			Order order = new Order(Direction.ASC, "id");
			Sort sort = new Sort(order);
			for (Customer bauer : repository.findAll(sort)) {
				log.info(bauer.toString());
			}
			log.info("");
			// 分页
			log.info("Customer found with page");
			log.info("--------------------------------------------");
			PageRequest page = new PageRequest(1, 2, sort);
			for (Customer bauer : repository.findAll(page)) {
				log.info(bauer.toString());
			}
			log.info("");			
		};
	}

}