package site.rion.PetShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.adminManage.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer>
{

	@Query(value="SELECT * FROM pet WHERE type='dog'",nativeQuery=true)
	public List<Pet> findAllDogs();
	
	@Query(value="SELECT * FROM pet WHERE type='cat'",nativeQuery=true)
	public List<Pet> findAllCats();
	
	public List<Pet> findAll();

	public Pet findById(int id);
}