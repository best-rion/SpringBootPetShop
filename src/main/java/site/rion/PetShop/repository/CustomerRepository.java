package site.rion.PetShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.employeeManage.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>
{
	public Customer findByUsername(String username);
	
	@Query(value="SELECT COUNT(1) FROM customer WHERE username = :username", nativeQuery=true)
	public byte checkIfUsernameExists(String username);
	
	List<Customer> findAll();
	
	@Query(value="SELECT * FROM customer WHERE full_name LIKE %:full_name%", nativeQuery=true)
	public List<Customer> findAllByFull_name(String full_name);
	
	@Query(value="SELECT * FROM customer WHERE username LIKE %:username%", nativeQuery=true)
	public List<Customer> findAllByUsername(String username);
	
	@Query(value="SELECT * FROM customer WHERE email LIKE %:email%", nativeQuery=true)
	public List<Customer> findAllByEmail(String email);
	
	@Query(value="SELECT * FROM customer WHERE phone LIKE %:phone%", nativeQuery=true)
	public List<Customer> findAllByPhone(String phone);
}

