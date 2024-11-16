package site.rion.PetShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.adminManage.Food;

public interface FoodRepository extends CrudRepository<Food, Integer>
{
	@Query(value="SELECT * FROM food WHERE type='dry'",nativeQuery=true)
	public List<Food> findAllDry();
	
	@Query(value="SELECT * FROM food WHERE type='wet'",nativeQuery=true)
	public List<Food> findAllWet();
	
	public Food findById(int id);
	
	public List<Food> findAll();
}