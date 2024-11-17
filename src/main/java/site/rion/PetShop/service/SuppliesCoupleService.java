package site.rion.PetShop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rion.PetShop.model.adminManage.Supplies;
import site.rion.PetShop.repository.SuppliesRepository;

@Service
public class SuppliesCoupleService
{
	@Autowired
	SuppliesRepository supplyRepo;
	
	
	public List<List<Supplies>> getSupplyCouples()
	{
		List<Supplies> supplies = supplyRepo.findAll();
		
		List<List<Supplies>> supplies2 = new ArrayList<>();
		
		int i=0;
		List<Supplies> supply2 = null;
		
		for (Supplies supply: supplies)
		{
			if( i%2==0 )
			{
				supply2 = new ArrayList<>();
				supply2.add(supply);
			}
			else
			{
				supply2.add(supply);
				supplies2.add(supply2);
			}
			
			if( i==supplies.size()-1 && supplies.size()%2==1 )
				supplies2.add(supply2);
			
			i++;
		}
		
		return supplies2;
	}
}