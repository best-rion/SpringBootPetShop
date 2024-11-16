package site.rion.PetShop.model.adminManage;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import site.rion.PetShop.form.SuppliesForm;
import site.rion.PetShop.model.Admin;

@Entity
public class Supplies
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private int price;
	private int quantity;
	@ManyToOne
	private Admin admin;
	private String image_suffix;
	
	public Supplies() {}
	

	public Supplies( SuppliesForm form )
	{
		this.name = form.getName();
		this.price = form.getPrice();
		this.quantity = form.getQuantity();
	}
	
	public void update( SuppliesForm form )
	{
		this.name = form.getName();
		this.price = form.getPrice();
		this.quantity = form.getQuantity();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}



	public String getImage_suffix() {
		return image_suffix;
	}



	public void setImage_suffix(String image_suffix) {
		this.image_suffix = image_suffix;
	}
}