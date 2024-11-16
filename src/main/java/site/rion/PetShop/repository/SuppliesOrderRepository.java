package site.rion.PetShop.repository;

import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.customerManage.SuppliesOrder;

public interface SuppliesOrderRepository extends CrudRepository<SuppliesOrder, Integer>
{
}