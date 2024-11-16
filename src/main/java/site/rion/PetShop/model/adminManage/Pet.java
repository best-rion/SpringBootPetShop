package site.rion.PetShop.model.adminManage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import site.rion.PetShop.form.PetForm;
import site.rion.PetShop.model.Admin;

@Entity
public class Pet
{	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private int age;
	private String species;
	private int price;
	private String type;
	@ManyToOne
	private Admin admin;
	private String image_suffix;
	
	public Pet() {}
	
	public Pet( PetForm form )
	{
		this.name = form.getName();
		this.age = form.getAge();
		this.species = form.getSpecies();
		this.price = form.getPrice();
		this.type = form.getType();
	}
	
	
	public void update( PetForm form)
	{
		this.name = form.getName();
		this.age = form.getAge();
		this.species = form.getSpecies();
		this.price = form.getPrice();
		this.type = form.getType();
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}

	public String getImage_suffix() {
		return image_suffix;
	}

	public void setImage_suffix(String image_suffix) {
		this.image_suffix = image_suffix;
	}
}