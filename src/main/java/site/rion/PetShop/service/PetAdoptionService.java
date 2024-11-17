package site.rion.PetShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rion.PetShop.model.adminManage.Pet;
import site.rion.PetShop.model.customerManage.Cart;
import site.rion.PetShop.model.customerManage.PetOrder;
import site.rion.PetShop.model.employeeManage.Customer;
import site.rion.PetShop.repository.CartRepository;

@Service
public class PetAdoptionService
{
	@Autowired
	CartRepository cartRepo;
	
	public void adoptFirstPet(Customer customer, Pet pet)
	{
		Cart newCart = new Cart( customer );
		
		List<PetOrder> petOrders = newCart.getPetOrders();
		petOrders.add( new PetOrder( pet ) );
		
		cartRepo.save( newCart );
	}
}