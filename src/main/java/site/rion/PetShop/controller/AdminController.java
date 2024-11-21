package site.rion.PetShop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import site.rion.PetShop.form.EmployeeForm;
import site.rion.PetShop.form.FoodForm;
import site.rion.PetShop.form.PetForm;
import site.rion.PetShop.form.SuppliesForm;
import site.rion.PetShop.model.Admin;
import site.rion.PetShop.model.adminManage.Employee;
import site.rion.PetShop.model.adminManage.Food;
import site.rion.PetShop.model.adminManage.Pet;
import site.rion.PetShop.model.adminManage.Supplies;
import site.rion.PetShop.repository.AdminRepository;
import site.rion.PetShop.repository.EmployeeRepository;
import site.rion.PetShop.repository.FoodRepository;
import site.rion.PetShop.repository.PetRepository;
import site.rion.PetShop.repository.SuppliesRepository;

@Controller
public class AdminController
{
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	PetRepository petRepo;
	
	@Autowired
	FoodRepository foodRepo;
	
	@Autowired
	SuppliesRepository suppliesRepo;
	
	@Value("${images.dir}")
	private String imagesFolder;

	
	
	@GetMapping("/admin")
	public String admin()
	{
		return "admin/admin";
	}
	
	
	@GetMapping("/admin/manageEmployee")
	public String manageEmployeeGet(Model model)
	{
		List<Employee> employees = employeeRepo.findAll();
		
		model.addAttribute("employees", employees);
		model.addAttribute("form", new EmployeeForm());
		return "admin/manageEmployee";
	}
	
	
	@PostMapping("/admin/manageEmployee")
	public String manageEmployeePost(@ModelAttribute EmployeeForm form, Principal principal, Model model)
	{
		Admin admin = adminRepo.findByUsername( principal.getName() );
		
		if (form.getAction().equals("add"))
		{
			int employeeExists = employeeRepo.checkIfUsernameExists( form.getUsername() );
			
			if (employeeExists == 0)
			{
				Employee employee = new Employee( form );
				employee.setAdmin( admin );
				employeeRepo.save( employee );
			}
			else
			{
				List<Employee> employees = employeeRepo.findAll();
				
				model.addAttribute("employees", employees);
				model.addAttribute("form", new EmployeeForm());
				model.addAttribute("usernameAlreadyExists", true);
				return "admin/manageEmployee";
			}
		}
		else if (form.getAction().equals("update"))
		{
			Employee employee = employeeRepo.findByUsername( form.getUsername() );
			employee.update(form);
			employeeRepo.save(employee);
		}
		else if (form.getAction().equals("remove"))
		{
			Employee employee = employeeRepo.findByUsername( form.getUsername() );
			employeeRepo.delete(employee);
		}
		
		return "redirect:/admin/manageEmployee";
	}
	
	
	@GetMapping("/admin/managePet")
	public String managePetGet(Model model)
	{
		List<Pet> pets = petRepo.findAll();
		
		model.addAttribute("pets", pets);
		model.addAttribute("form", new PetForm());
		return "admin/managePet";
	}
	
	
	@PostMapping("/admin/managePet")
	public String managePetPost(@ModelAttribute PetForm form, Principal principal, @RequestParam("image") MultipartFile file ) throws IOException
	{
		Admin admin = adminRepo.findByUsername( principal.getName() );

		if (form.getAction().equals("add"))
		{
	        String fileName = file.getOriginalFilename();
			String suffix[] = fileName.split("\\.");
			
			Pet pet = new Pet( form );
			pet.setAdmin( admin );
			pet.setImage_suffix( suffix[1] );
			petRepo.save( pet );
			

			Path path = Paths.get(imagesFolder, "pet", form.getType(), Integer.toString( pet.getId() ) + "." + suffix[1]);
			Files.write( path, file.getBytes());
			
		}
		else if (form.getAction().equals("update"))
		{
			Pet pet = petRepo.findById( form.getId() );
			pet.update( form );
			petRepo.save(pet);
		}
		
		return "redirect:/admin/managePet";
	}
	
	
	
	@GetMapping("/admin/manageFood")
	public String manageFoodGet(Model model)
	{
		List<Food> foods =foodRepo.findAll();
	
		model.addAttribute("foods",foods);
		model.addAttribute("form", new FoodForm());
		return "admin/manageFood";
	}
	
	
	@PostMapping("/admin/manageFood")
	public String manageFoodPost(@ModelAttribute FoodForm form, Principal principal, @RequestParam("image") MultipartFile file ) throws IOException
	{
		Admin admin = adminRepo.findByUsername( principal.getName() );
		
		if (form.getAction().equals("add"))
		{
	        String fileName = file.getOriginalFilename();
			String suffix[] = fileName.split("\\.");
			
			Food food = new Food( form );
			food.setAdmin( admin );
			food.setImage_suffix( suffix[1] );
			foodRepo.save( food );
			
			Path path = Paths.get(imagesFolder,"food", form.getType(), Integer.toString( food.getId() ) + "." + suffix[1]);
			Files.write( path, file.getBytes());
			
		}
		else if (form.getAction().equals("update"))
		{
			Food food = foodRepo.findById( form.getId() );
			food.update( form );
			foodRepo.save(food);
		}
		else if (form.getAction().equals("remove"))
		{
			Food food = foodRepo.findById( form.getId() );
			foodRepo.delete(food);
		}
		
		return "redirect:/admin/manageFood";
	}
	
	
	
	@GetMapping("/admin/manageSupplies")
	public String manageSuppliesGet(Model model)
	{
		List<Supplies> supplies = suppliesRepo.findAll();
	
		model.addAttribute("supplies", supplies);
		model.addAttribute("form", new SuppliesForm());
		return "admin/manageSupplies";
	}
	
	
	@PostMapping("/admin/manageSupplies")
	public String manageSuppliesPost(@ModelAttribute SuppliesForm form, Principal principal,  @RequestParam("image") MultipartFile file ) throws IOException
	{
		Admin admin = adminRepo.findByUsername( principal.getName() );
		
		if (form.getAction().equals("add"))
		{
	        String fileName = file.getOriginalFilename();
			String suffix[] = fileName.split("\\.");
			
			
			Supplies supplies = new Supplies( form );
			supplies.setAdmin( admin );
			supplies.setImage_suffix( suffix[1] );
			suppliesRepo.save( supplies );
			
			
			Path path = Paths.get(imagesFolder, "supplies", Integer.toString( supplies.getId() ) + "." + suffix[1]);
			Files.write( path, file.getBytes());
		}

		else if (form.getAction().equals("update"))
		{
			Supplies supply = suppliesRepo.findById( form.getId() );
			supply.update( form );
			suppliesRepo.save(supply);
		}
		else if (form.getAction().equals("remove"))
		{
			Supplies supply = suppliesRepo.findById( form.getId() );
			suppliesRepo.delete(supply);
		}
		
		return "redirect:/admin/manageSupplies";
	}
}