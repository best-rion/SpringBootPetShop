package site.rion.PetShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import site.rion.PetShop.repository.AdminRepository;
import site.rion.PetShop.repository.CustomerRepository;
import site.rion.PetShop.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{	
		int customerExists = customerRepo.checkIfUsernameExists(username);
		if (customerExists == 1)
		{
			return customerRepo.findByUsername(username);
		}
		
		int employeeExists = employeeRepo.checkIfUsernameExists(username);
		if (employeeExists == 1)
		{
			return employeeRepo.findByUsername(username);
		}
		
		int adminExists = adminRepo.checkIfUsernameExists(username);
		if (adminExists == 1)
		{
			return adminRepo.findByUsername(username);
		}
		return null;
	}
	
}