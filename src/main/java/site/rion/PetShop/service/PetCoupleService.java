package site.rion.PetShop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rion.PetShop.model.adminManage.Pet;
import site.rion.PetShop.repository.PetRepository;

@Service
public class PetCoupleService
{
	@Autowired
	PetRepository petRepo;
	
	
	public List<List<Pet>> getDogCouples()
	{
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
		
		return pets2;
	}
	
	public List<List<Pet>> getCatCouples()
	{
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
		
		return pets2;
	}
}