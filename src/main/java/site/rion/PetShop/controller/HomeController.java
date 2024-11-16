package site.rion.PetShop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.rion.PetShop.model.adminManage.Food;
import site.rion.PetShop.model.adminManage.Pet;
import site.rion.PetShop.model.adminManage.Supplies;
import site.rion.PetShop.repository.FoodRepository;
import site.rion.PetShop.repository.PetRepository;
import site.rion.PetShop.repository.SuppliesRepository;

@Controller
public class HomeController
{
	@Autowired
	PetRepository petRepo;
	
	@Autowired
	FoodRepository foodRepo;
	
	@Autowired
	SuppliesRepository suppliesRepo;
	
	
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
		// Extract dogs from database
		List<Pet> pets = petRepo.findAllDogs();
		
		List<List<Pet>> pets2 = new ArrayList<>();
		
		int i=0;
		List<Pet> pet2 = null;
		
		for (Pet pet: pets)
		{
			if( i%2==0 )
			{
				pet2 = new ArrayList<>();
				pet2.add(pet);
			}
			else
			{
				pet2.add(pet);
				pets2.add(pet2);
			}
			
			if( i==pets.size()-1 && pets.size()%2==1 )
				pets2.add(pet2);
			
			i++;
		}
		
		model.addAttribute("pets2", pets2);
		return "customer/pet/dog";
	}
	
	
	@GetMapping("/pet/cat")
	public String cat(Model model)
	{
		// Extract dogs from database
		List<Pet> pets = petRepo.findAllCats();
		
		List<List<Pet>> pets2 = new ArrayList<>();
		
		int i=0;
		List<Pet> pet2 = null;
		
		for (Pet pet: pets)
		{
			if( i%2==0 )
			{
				pet2 = new ArrayList<>();
				pet2.add(pet);
			}
			else
			{
				pet2.add(pet);
				pets2.add(pet2);
			}
			
			if( i==pets.size()-1 && pets.size()%2==1 )
				pets2.add(pet2);
			
			i++;
		}
		
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
		// Extract dogs from database
		List<Food> foods = foodRepo.findAllDry();
		
		List<List<Food>> foods2 = new ArrayList<>();
		
		int i=0;
		List<Food> food2 = null;
		
		for (Food food: foods)
		{
			if( i%2==0 )
			{
				food2 = new ArrayList<>();
				food2.add(food);
			}
			else
			{
				food2.add(food);
				foods2.add(food2);
			}
			
			if( i==foods.size()-1 && foods.size()%2==1 )
				foods2.add(food2);
			
			i++;
		}
		
		model.addAttribute("foods2", foods2);
		return "customer/food/dry";
	}
	
	
	@GetMapping("/food/wet")
	public String wet( Model model )
	{
		// Extract dogs from database
		List<Food> foods = foodRepo.findAllWet();
		
		List<List<Food>> foods2 = new ArrayList<>();
		
		int i=0;
		List<Food> food2 = null;
		
		for (Food food: foods)
		{
			if( i%2==0 )
			{
				food2 = new ArrayList<>();
				food2.add(food);
			}
			else
			{
				food2.add(food);
				foods2.add(food2);
			}
			
			if( i==foods.size()-1 && foods.size()%2==1 )
				foods2.add(food2);
			
			i++;
		}
		
		model.addAttribute("foods2", foods2);
		return "customer/food/wet";
	}
	
	
	@GetMapping("/supplies")
	public String supplies(Model model)
	{
		// Extract dogs from database
		List<Supplies> supplies = suppliesRepo.findAll();
		
		List<List<Supplies>> supplies2 = new ArrayList<>();
		
		int i=0;
		List<Supplies> supply2 = null;
		
		for (Supplies food: supplies)
		{
			if( i%2==0 )
			{
				supply2 = new ArrayList<>();
				supply2.add(food);
			}
			else
			{
				supply2.add(food);
				supplies2.add(supply2);
			}
			
			if( i==supplies.size()-1 && supplies.size()%2==1 )
				supplies2.add(supply2);
			
			i++;
		}
		
		model.addAttribute("supplies2", supplies2);
		return "customer/supplies";
	}
}