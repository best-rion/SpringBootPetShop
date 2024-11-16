package site.rion.PetShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.adminManage.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{
	public Employee findByUsername(String username);
	
	@Query(value="SELECT COUNT(1) FROM employee WHERE username = :username", nativeQuery=true)
	public byte checkIfUsernameExists(String username);
	
	public List<Employee> findAll(); 
}

