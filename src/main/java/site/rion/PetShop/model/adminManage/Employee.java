package site.rion.PetShop.model.adminManage;

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
import jakarta.persistence.ManyToOne;
import site.rion.PetShop.form.EmployeeForm;
import site.rion.PetShop.model.Admin;

@Entity
public class Employee implements UserDetails
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String full_name;
	@Column(unique=true)
	private String username;
	private int age;
	private char gender;
	private String phone;
	private String password;
	private long salary;
	private int workhour;
	@ManyToOne
	private Admin admin;
	
	public Employee() {}
	
	public Employee( EmployeeForm form )
	{
		this.full_name = form.getFull_name();
		this.username = form.getUsername();
		this.age = form.getAge();
		this.gender = form.getGender();
		this.phone = form.getPhone();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode( form.getPassword() );
		
		this.salary = form.getSalary();
		this.workhour = form.getWorkhour();
	}
	
	public void update( EmployeeForm form )
	{
		this.full_name = form.getFull_name();
		this.age = form.getAge();
		this.gender = form.getGender();
		this.phone = form.getPhone();
		this.salary = form.getSalary();
		this.workhour = form.getWorkhour();
	}
	
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public int getWorkhour() {
		return workhour;
	}
	public void setWorkhour(int workhour) {
		this.workhour = workhour;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
		authority.add(new SimpleGrantedAuthority("EMPLOYEE"));
		return authority;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}