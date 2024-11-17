package site.rion.PetShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rion.PetShop.model.adminManage.Food;
import site.rion.PetShop.model.customerManage.Cart;
import site.rion.PetShop.model.customerManage.FoodOrder;
import site.rion.PetShop.model.employeeManage.Customer;
import site.rion.PetShop.repository.CartRepository;

@Service
public class FoodBuyService
{
	@Autowired
	CartRepository cartRepo;
	
	public void buyFirstFood(Customer customer, Food food)
	{
		Cart newCart = new Cart( customer );
		
		List<FoodOrder> petOrders = newCart.getFoodOrders();
		petOrders.add( new FoodOrder( food ) );
		
		cartRepo.save( newCart );
	}
}