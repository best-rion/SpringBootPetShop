package site.rion.PetShop.model.employeeManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import site.rion.PetShop.form.CustomerForm;

@Entity
public class Customer implements UserDetails
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String full_name;
	@Column(unique=true)
	private String username;
	private char gender;
	private String password;
	private String phone;
	@Column(unique=true)
	private String email;
	
	public Customer() {}
	
	public Customer( CustomerForm form )
	{
		this.full_name = form.getFull_name();
		this.username = form.getUsername();
		this.gender = form.getGender();
		this.phone = form.getPhone();
		this.email = form.getEmail();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode( form.getPassword() );
	}
	
	public void update( CustomerForm form )
	{
		this.full_name = form.getFull_name();
		this.gender = form.getGender();
		this.phone = form.getPhone();
		this.email = form.getEmail();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		this.password = encoder.encode(password);
	}
	@Override
	public List<GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority("CUSTOMER"));
		return authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}