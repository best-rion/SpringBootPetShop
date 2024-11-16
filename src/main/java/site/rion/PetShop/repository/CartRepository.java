package site.rion.PetShop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.customerManage.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>
{
	
	
	
	@Query(value="SELECT COUNT(1) FROM cart WHERE customer_id = :customerId", nativeQuery=true)
	public int activeOrderExists(int customerId);
	
	@Query(value="SELECT * FROM cart WHERE customer_id = :customerId", nativeQuery=true)
	public Cart findByCustomerId(int customerId);
	
	public Cart findById( int id );
	
	public List<Cart> findAll();
}