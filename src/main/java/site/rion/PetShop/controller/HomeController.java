package site.rion.PetShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.rion.PetShop.model.adminManage.Food;
import site.rion.PetShop.model.adminManage.Pet;
import site.rion.PetShop.model.adminManage.Supplies;
import site.rion.PetShop.service.FoodCoupleService;
import site.rion.PetShop.service.PetCoupleService;
import site.rion.PetShop.service.SuppliesCoupleService;

@Controller
public class HomeController
{
	@Autowired
	PetCoupleService petCoupleService;
	
	@Autowired
	FoodCoupleService foodCoupleService;
	
	@Autowired
	SuppliesCoupleService suppliesCoupleService;
	
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	
	@GetMapping("/pet")
	public String pet()
	{
		return "customer/pet";
	}
	
	
	@GetMapping("/pet/dog")
	public String dog(Model model)
	{
		List<List<Pet>> pets2 = petCoupleService.getDogCouples();
		
		model.addAttribute("pets2", pets2);
		return "customer/pet/dog";
	}
	
	
	@GetMapping("/pet/cat")
	public String cat(Model model)
	{
		List<List<Pet>> pets2 = petCoupleService.getCatCouples();
		
		model.addAttribute("pets2", pets2);
		return "customer/pet/cat";
	}
	
	
	@GetMapping("/food")
	public String food()
	{
		return "customer/food";
	}
	
	
	@GetMapping("/food/dry")
	public String dry(Model model)
	{
		List<List<Food>> foods2 = foodCoupleService.getDryCouples();
		
		model.addAttribute("foods2", foods2);
		return "customer/food/dry";
	}
	
	
	@GetMapping("/food/wet")
	public String wet( Model model )
	{
		List<List<Food>> foods2 = foodCoupleService.getWetCouples();
		
		model.addAttribute("foods2", foods2);
		return "customer/food/wet";
	}
	
	
	@GetMapping("/supplies")
	public String supplies(Model model)
	{
		List<List<Supplies>> supplies2 = suppliesCoupleService.getSupplyCouples();
		
		model.addAttribute("supplies2", supplies2);
		return "customer/supplies";
	}
}