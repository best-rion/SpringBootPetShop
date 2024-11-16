package site.rion.PetShop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>
{
	public Admin findByUsername(String username);
	
	@Query(value="SELECT COUNT(1) FROM admin WHERE username = :username", nativeQuery=true)
	public byte checkIfUsernameExists(String username);
}

