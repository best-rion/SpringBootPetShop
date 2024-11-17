package site.rion.PetShop.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import site.rion.PetShop.form.CustomerForm;
import site.rion.PetShop.form.SearchForm;
import site.rion.PetShop.model.customerManage.Cart;
import site.rion.PetShop.model.customerManage.FoodOrder;
import site.rion.PetShop.model.customerManage.PetOrder;
import site.rion.PetShop.model.customerManage.SuppliesOrder;
import site.rion.PetShop.model.employeeManage.Customer;
import site.rion.PetShop.repository.CartRepository;
import site.rion.PetShop.repository.CustomerRepository;
import site.rion.PetShop.util.PdfCreator;

@Controller
public class EmployeeController
{
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CartRepository cartRepo;
	

	@GetMapping("/employee")
	public String employee()
	{
		return "employee/employee";
	}
	
	
	@GetMapping("/employee/manageCustomer")
	public String manageCustomerGet(Model model)
	{
		List<Customer> customers = customerRepo.findAll();
		
		model.addAttribute("customers", customers);
		model.addAttribute("form", new CustomerForm());
		return "employee/manageCustomer";
	}
	
	
	@PostMapping("/employee/manageCustomer")
	public String manageCustomerPost(@ModelAttribute CustomerForm form, Model model)
	{
		if (form.getAction().equals("add"))
		{
			int customerExists = customerRepo.checkIfUsernameExists( form.getUsername() );
			
			if (customerExists == 0)
			{
				Customer customer = new Customer( form );
				customerRepo.save( customer );
			}
			else
			{
				List<Customer> customers = customerRepo.findAll();
				
				model.addAttribute("customers", customers);
				model.addAttribute("form", new CustomerForm());
				model.addAttribute("usernameAlreadyExists", true);
				return "admin/manageEmployee";
			}
		}
		else if (form.getAction().equals("update"))
		{
			Customer customer = customerRepo.findByUsername( form.getUsername() );
			customer.update(form);
			customerRepo.save(customer);
		}
		else if (form.getAction().equals("remove"))
		{
			Customer customer = customerRepo.findByUsername( form.getUsername() );
			customerRepo.delete(customer);
		}
		
		return "redirect:/employee/manageCustomer";
	}
	
	
	@GetMapping("/employee/manageOrder")
	public String manageOrderGet(Model model)
	{
		List<Cart> carts = cartRepo.findAll();
		
		List<Customer> customers = new ArrayList<>();
		
		for (Cart cart: carts)
		{
			customers.add( cart.getCustomer() );
		}
		
		model.addAttribute("customers", customers);
		model.addAttribute("form", new SearchForm());
		return "employee/manageOrder";
	}
	
	@PostMapping("/employee/manageOrder")
	public String manageOrderPost(@ModelAttribute SearchForm form, Model model)
	{
		List<Customer> customers = null;
		
		if ( form.getBy().equals( "name" ) )
		{
			customers = customerRepo.findAllByFull_name( form.getTerm() );
		}
		else if ( form.getBy().equals( "username" ) )
		{
			customers = customerRepo.findAllByUsername( form.getTerm() );
		}
		else if ( form.getBy().equals( "email" ) )
		{
			customers = customerRepo.findAllByEmail( form.getTerm() );
		}
		else if ( form.getBy().equals( "phone" ) )
		{
			customers = customerRepo.findAllByPhone( form.getTerm() );
		}
		
		
		if (customers.size() > 0)
		{
			for(Customer customer: customers)
			{
				System.out.print(customer.getUsername());
			}
		}
		
		model.addAttribute("customers", customers);
		return null;
	}
	
	
	@GetMapping("/employee/manageOrder/{username}") //Searching, not editing
	public String manageOrderGet(Model model, @PathVariable String username)
	{
		
		Customer customer = customerRepo.findByUsername( username );
		
		Cart cart = cartRepo.findByCustomerId( customer.getId() );
		
		float total = 0;
		
		for (PetOrder petOrder: cart.getPetOrders())
		{
			total += petOrder.getPet().getPrice();
		}
		
		for (FoodOrder foodOrder: cart.getFoodOrders())
		{
			total += foodOrder.getFood().getPrice() * foodOrder.getQuantity();
		}
		
		for (SuppliesOrder suppliesOrder: cart.getSuppliesOrders())
		{
			total += suppliesOrder.getSupply().getPrice() * suppliesOrder.getQuantity();
		}
		
		model.addAttribute("cartId", cart.getId());
		model.addAttribute("customerId", customer.getId());
		model.addAttribute("totalAmount", total);
		
		
		return "employee/Order";
	}
	
	
	@PostMapping("/employee/print")
	public void manageOrderPost(@RequestParam("id") String id, HttpServletResponse response)
	{
		Cart cart = cartRepo.findById( Integer.parseInt(id) );
		
		try
		{
			ByteArrayOutputStream os = PdfCreator.create(cart);
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			
			response.setContentType("application/pdf");
			response.setContentLengthLong(os.size());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
										                    .filename("receipt.pdf", StandardCharsets.UTF_8)
										                    .build()
										                    .toString());
			is.transferTo(response.getOutputStream());
		}
		catch(IOException | DocumentException | URISyntaxException  ex)
		{
			ex.printStackTrace();
		}
		
		cartRepo.delete(cart);
	}
	
}



