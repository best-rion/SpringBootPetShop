package site.rion.PetShop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.adminManage.Supplies;

public interface SuppliesRepository extends CrudRepository<Supplies, Integer>
{
	public List<Supplies> findAll();
	
	public Supplies findById(int id);
}