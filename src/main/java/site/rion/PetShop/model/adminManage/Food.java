package site.rion.PetShop.model.adminManage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import site.rion.PetShop.form.FoodForm;
import site.rion.PetShop.model.Admin;

@Entity
public class Food
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String vendor;
	private String type;
	private int quantity;
	private int price;
	@ManyToOne
	private Admin admin;
	private String image_suffix;
	
	public Food() {}
	
	public Food( FoodForm form )
	{
		this.name = form.getName();
		this.vendor = form.getVendor();
		this.type = form.getType();
		this.quantity = form.getQuantity();
		this.price = form.getQuantity();
	}
	
	
	public void update( FoodForm form )
	{
		this.name = form.getName();
		this.vendor = form.getVendor();
		this.type = form.getType();
		this.quantity = form.getQuantity();
		this.price = form.getQuantity();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage_suffix() {
		return image_suffix;
	}

	public void setImage_suffix(String image_suffix) {
		this.image_suffix = image_suffix;
	}
	
}