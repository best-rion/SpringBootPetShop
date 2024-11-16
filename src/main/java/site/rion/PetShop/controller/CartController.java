package site.rion.PetShop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.rion.PetShop.model.customerManage.Cart;
import site.rion.PetShop.model.customerManage.FoodOrder;
import site.rion.PetShop.model.customerManage.PetOrder;
import site.rion.PetShop.model.customerManage.SuppliesOrder;
import site.rion.PetShop.model.employeeManage.Customer;
import site.rion.PetShop.repository.CartRepository;
import site.rion.PetShop.repository.CustomerRepository;

@Controller
public class CartController
{
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@GetMapping("/cart")
	public String cart(Model model, Principal principal)
	{
		Customer customer = customerRepo.findByUsername( principal.getName() );
		Cart cart = cartRepo.findByCustomerId(customer.getId());

		float petCost=0, foodCost=0, suppliesCost=0;
		
		if (cart==null)
		{
			cart = new Cart( customer );
		}
		else
		{
			for (PetOrder petOrder: cart.getPetOrders())
			{
				petCost += petOrder.getPet().getPrice();
			}
			
			for (FoodOrder foodOrder: cart.getFoodOrders())
			{
				foodCost += foodOrder.getFood().getPrice() * foodOrder.getQuantity();
			}
			
			for (SuppliesOrder suppliesOrder: cart.getSuppliesOrders())
			{
				suppliesCost += suppliesOrder.getSupply().getPrice() * suppliesOrder.getQuantity();
			}
		}
		
		model.addAttribute("cart", cart);
		model.addAttribute("petCost", petCost);
		model.addAttribute("foodCost", foodCost);
		model.addAttribute("suppliesCost", suppliesCost);
		model.addAttribute("totalCost", petCost + foodCost + suppliesCost);

		return "cart";
	}
}