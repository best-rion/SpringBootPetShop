package site.rion.PetShop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import site.rion.PetShop.form.SignupForm;
import site.rion.PetShop.model.Admin;
import site.rion.PetShop.repository.AdminRepository;

@Controller
public class AuthenticationController
{
	@Autowired
	AdminRepository adminRepo;
	
	@GetMapping("/login")
	public String login(Model model)
	{
		return "login";
	}
	
	@GetMapping("/loginSuccess")
	public void getLoginInfo(
	        @AuthenticationPrincipal UserDetails authentication,
	        HttpServletResponse response) throws IOException {
		System.out.println("HELOO ");
	    if (authentication.getAuthorities()
	            .contains(new SimpleGrantedAuthority("ADMIN"))) {
	        response.sendRedirect("/admin");
	    }
	    else if (authentication.getAuthorities()
	            .contains(new SimpleGrantedAuthority("EMPLOYEE")))
	    {
	    	response.sendRedirect("/employee");
	    }
	    
	    else {
	        response.sendRedirect("/");
	    }
	}
	
	
	@GetMapping("/signup")
	public String signupGet(Model model)
	{
		model.addAttribute("form", new SignupForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute SignupForm form, Model model)
	{
		if ( adminRepo.checkIfUsernameExists(form.getUsername()) != 0 )
		{
			model.addAttribute("usernameAlreadyExists", true);
			return "signup";
		}
		Admin admin = new Admin( form );
		adminRepo.save( admin );
		
		return "redirect:/login";
	}
}