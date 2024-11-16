package site.rion.PetShop.controller;

import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import site.rion.PetShop.model.adminManage.Food;
import site.rion.PetShop.model.adminManage.Pet;
import site.rion.PetShop.model.adminManage.Supplies;
import site.rion.PetShop.model.customerManage.Cart;
import site.rion.PetShop.model.customerManage.FoodOrder;
import site.rion.PetShop.model.customerManage.PetOrder;
import site.rion.PetShop.model.customerManage.SuppliesOrder;
import site.rion.PetShop.model.employeeManage.Customer;
import site.rion.PetShop.repository.CartRepository;
import site.rion.PetShop.repository.CustomerRepository;
import site.rion.PetShop.repository.FoodOrderRepository;
import site.rion.PetShop.repository.FoodRepository;
import site.rion.PetShop.repository.PetOrderRepository;
import site.rion.PetShop.repository.PetRepository;
import site.rion.PetShop.repository.SuppliesOrderRepository;
import site.rion.PetShop.repository.SuppliesRepository;

@RestController
public class HomeRestController
{
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	PetRepository petRepo;
	
	@Autowired
	PetOrderRepository petOrderRepo;
	
	@Autowired
	FoodRepository foodRepo;
	
	@Autowired
	FoodOrderRepository foodOrderRepo;
	
	@Autowired
	SuppliesRepository suppliesRepo;
	
	@Autowired
	SuppliesOrderRepository suppliesOrderRepo;
	
	
	@PutMapping("/pet/adopt")
	public String adopt(@RequestBody String id, Principal principal)
	{
		System.out.println(id);
		// add pet to cart;

		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		int activeOrderExists = cartRepo.activeOrderExists( customer.getId() );
		
		Pet pet = petRepo.findById( Integer.parseInt(id) );
		
		if ( activeOrderExists == 0 )
		{
			Cart newCart = new Cart( customer );

			PetOrder petOrder = new PetOrder( pet );
			
			List<PetOrder> petOrders = newCart.getPetOrders();
			
			petOrders.add( petOrder );
			
			cartRepo.save( newCart );
			
			petOrderRepo.save(petOrder);
			
		}
		else
		{
			// check if that customer already has the pet
			Cart cart = cartRepo.findByCustomerId( customer.getId() );
			
			List<PetOrder> petOrders = cart.getPetOrders();
			
			if( petOrders.size() == 0 )
			{
				PetOrder petOrder = new PetOrder( pet );
				
				petOrders.add(petOrder);
				
				cartRepo.save( cart );
				
				petOrderRepo.save( petOrder );
			}
			else
			{
				boolean thisPetAlreadyThere = false;
				
				for (PetOrder petOrder: petOrders)
				{
					if(petOrder.getPet().equals(pet))
					{
						thisPetAlreadyThere = true;
					}
				}
				
				if( ! thisPetAlreadyThere )
				{
					PetOrder petOrder = new PetOrder( pet );
					
					petOrders.add(petOrder);
					
					cartRepo.save( cart );
					
					petOrderRepo.save( petOrder );
				}
				else
				{
					// do nothing
				}
				
			}
		}
		
		
		return "1";
	}
	
	
	@PutMapping("/food/buy")
	public String buyFood(@RequestBody String id, Principal principal)
	{
		Customer customer = customerRepo.findByUsername( principal.getName() );
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
	
		Food food = foodRepo.findById( Integer.parseInt(id) );
		
		if ( cart == null )
		{
			cart = new Cart( customer );

			FoodOrder foodOrder = new FoodOrder( food );
			foodOrder.setQuantity( 1 );
			
			List<FoodOrder> foodOrders = cart.getFoodOrders();
			foodOrders.add( foodOrder );
			
			cartRepo.save( cart );
		}
		else
		{
			List<FoodOrder> foodOrders = cart.getFoodOrders();
			
			if( foodOrders.size() == 0 )
			{
				FoodOrder foodOrder = new FoodOrder( food );
				foodOrder.setQuantity( 1 );
				
				foodOrders.add(foodOrder);
				
				cartRepo.save( cart );
			}
			else
			{
				boolean thisFoodAlreadyThere = false;
				
				for (FoodOrder foodOrder: foodOrders)
				{
					if(foodOrder.getFood().equals( food ))
					{
						thisFoodAlreadyThere = true;
					}
				}
				
				if( !thisFoodAlreadyThere )
				{
					FoodOrder foodOrder = new FoodOrder( food );
					foodOrder.setQuantity( 1 );
					
					foodOrders.add(foodOrder);
					
					cartRepo.save( cart );
				}
				else
				{
					// do nothing
				}
			}
		}
		
		return "1";
	}
	
	
	@PutMapping("/supplies/buy")
	public String buySupplies(@RequestBody String id, Principal principal)
	{
		System.out.println(id);
		// add food to cart;

		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		int activeOrderExists = cartRepo.activeOrderExists( customer.getId() );
		
		Supplies supply = suppliesRepo.findById( Integer.parseInt(id) );
		
		if ( activeOrderExists == 0 )
		{
			Cart newCart = new Cart( customer );

			SuppliesOrder suppliesOrder = new SuppliesOrder( supply );
			suppliesOrder.setQuantity( 1 );
			
			List<SuppliesOrder> suppliesOrders = newCart.getSuppliesOrders();
			
			suppliesOrders.add( suppliesOrder );
			
			cartRepo.save( newCart );
			
			suppliesOrderRepo.save(suppliesOrder);
			
		}
		else
		{
			// check if that customer already has the pet
			Cart cart = cartRepo.findByCustomerId( customer.getId() );
			
			List<SuppliesOrder> suppliesOrders = cart.getSuppliesOrders();
			
			if( suppliesOrders.size() == 0 )
			{
				SuppliesOrder suppliesOrder = new SuppliesOrder( supply );
				suppliesOrder.setQuantity( 1 );
				
				suppliesOrders.add(suppliesOrder);
				
				cartRepo.save( cart );
				
				suppliesOrderRepo.save( suppliesOrder );
			}
			else
			{
				boolean thisSupplyAlreadyThere = false;
				
				for (SuppliesOrder suppliesOrder: suppliesOrders)
				{
					if(suppliesOrder.getSupply().equals( supply ))
					{
						thisSupplyAlreadyThere = true;
					}
				}
				
				if( ! thisSupplyAlreadyThere )
				{
					SuppliesOrder suppliesOrder = new SuppliesOrder( supply );
					suppliesOrder.setQuantity( 1 );
					
					suppliesOrders.add(suppliesOrder);
					
					cartRepo.save( cart );
					
					suppliesOrderRepo.save( suppliesOrder );
				}
				else
				{
					// do nothing
				}
				
			}
		}
		
		
		return "1";
	}
	
	
	@GetMapping("/image")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("product") String product, @RequestParam("type") String type, @RequestParam("id") String id, @RequestParam("image_suffix") String image_suffix) {
	    MediaType contentType = (image_suffix=="png") ?  MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
	    

	    product = "/" + product;
	    if (!type.equals( "null" ))
	    {
	    	type = "/" + type;
	    }
	    else
	    {
	    	type = "";
	    }
	    id = "/" + id;
	    
	    
	    InputStream in = getClass().getResourceAsStream("/static/images"+product+type+id+"."+image_suffix);
	    return ResponseEntity.ok()
	      .contentType(contentType)
	      .body(new InputStreamResource(in));
	}
	
	

	@PutMapping("/update/food/{id}")
	public String foodUpdate(@PathVariable String id, @RequestBody String quantity, Principal principal)
	{
		
		System.out.println("id="+id+",quantity="+quantity);
		
		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
		
		List<FoodOrder> foodOrders = cart.getFoodOrders();
		
		for (FoodOrder foodOrder: foodOrders)
		{
			if ( foodOrder.getFood().getId() == Integer.parseInt(id) )
			{
				foodOrder.setQuantity( Integer.parseInt(quantity) );
			}
		}
		
		cartRepo.save(cart);
		
		return "1";
	}

	@PutMapping("/update/supplies/{id}")
	public String suppliesUpdate(@PathVariable String id, @RequestBody String quantity, Principal principal)
	{
		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
		
		List<SuppliesOrder> suppliesOrders = cart.getSuppliesOrders();
		
		for (SuppliesOrder suppliesOrder: suppliesOrders)
		{
			if ( suppliesOrder.getSupply().getId() == Integer.parseInt(id) )
			{
				suppliesOrder.setQuantity( Integer.parseInt(quantity) );
			}
		}
		
		cartRepo.save(cart);
		
		return "1";
	}
	
	
	@DeleteMapping("/remove/pet/{id}")
	public String petRemove(@PathVariable String id, Principal principal)
	{
		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
		
		List<PetOrder> petOrders = cart.getPetOrders();
		
		for (PetOrder petOrder: petOrders)
		{
			if ( petOrder.getPet().getId() == Integer.parseInt(id) )
			{
				petOrders.remove( petOrder );
				break;
			}
		}
		
		cart.setPetOrders(petOrders);
		cartRepo.save(cart);
		
		return "1";
	}


	@DeleteMapping("/remove/food/{id}")
	public String foodRemove(@PathVariable String id, Principal principal)
	{
		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
		
		List<FoodOrder> foodOrders = cart.getFoodOrders();
		
		for (FoodOrder foodOrder: foodOrders)
		{
			if ( foodOrder.getFood().getId() == Integer.parseInt(id) )
			{
				foodOrders.remove( foodOrder );
				break;
			}
		}
		
		cart.setFoodOrders(foodOrders);
		cartRepo.save(cart);
		
		return "1";
	}
	

	@DeleteMapping("/remove/supplies/{id}")
	public String suppliesRemove(@PathVariable String id, Principal principal)
	{
		Customer customer = customerRepo.findByUsername( principal.getName() );
		
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
		
		List<SuppliesOrder> suppliesOrders = cart.getSuppliesOrders();
		
		for (SuppliesOrder suppliesOrder: suppliesOrders)
		{
			if ( suppliesOrder.getSupply().getId() == Integer.parseInt(id) )
			{
				suppliesOrders.remove( suppliesOrder );
			}
		}
		
		cart.setSuppliesOrders(suppliesOrders);
		cartRepo.save(cart);
		return "1";
	}
}

