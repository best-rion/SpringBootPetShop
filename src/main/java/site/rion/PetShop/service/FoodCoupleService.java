package site.rion.PetShop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rion.PetShop.model.adminManage.Food;
import site.rion.PetShop.repository.FoodRepository;

@Service
public class FoodCoupleService
{
	@Autowired
	FoodRepository foodRepo;
	
	public List<List<Food>> getDryCouples()
	{
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
		
		return foods2;
	}
	
	public List<List<Food>> getWetCouples()
	{
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
		
		return foods2;
	}
}