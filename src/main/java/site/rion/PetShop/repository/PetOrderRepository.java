package site.rion.PetShop.repository;

import org.springframework.data.repository.CrudRepository;

import site.rion.PetShop.model.customerManage.PetOrder;

public interface PetOrderRepository extends CrudRepository<PetOrder, Integer>
{
}