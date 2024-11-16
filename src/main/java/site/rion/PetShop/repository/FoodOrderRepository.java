package site.rion.PetShop.repository;

import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.customerManage.FoodOrder;

public interface FoodOrderRepository extends CrudRepository<FoodOrder, Integer>
{
}