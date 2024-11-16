package site.rion.PetShop.model.customerManage;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import site.rion.PetShop.model.employeeManage.Customer;

@Entity
public class Cart
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Customer customer;
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<PetOrder> petOrders;
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<FoodOrder> foodOrders;
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<SuppliesOrder> suppliesOrders;
	
	
	public Cart() {}
	
	
	public Cart(Customer customer)
	{
		this.customer = customer;
		
		this.petOrders = new ArrayList<>();
		this.foodOrders = new ArrayList<>();
		this.suppliesOrders = new ArrayList<>();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<PetOrder> getPetOrders() {
		return petOrders;
	}
	public void setPetOrders(List<PetOrder> petOrders) {
		this.petOrders = petOrders;
	}
	public List<FoodOrder> getFoodOrders() {
		return foodOrders;
	}
	public void setFoodOrders(List<FoodOrder> foodOrders) {
		this.foodOrders = foodOrders;
	}
	public List<SuppliesOrder> getSuppliesOrders() {
		return suppliesOrders;
	}
	public void setSuppliesOrders(List<SuppliesOrder> suppliesOrders) {
		this.suppliesOrders = suppliesOrders;
	}
}