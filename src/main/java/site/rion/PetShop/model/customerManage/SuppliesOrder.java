package site.rion.PetShop.model.customerManage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import site.rion.PetShop.model.adminManage.Supplies;

@Entity
public class SuppliesOrder
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Supplies supply;
	private int quantity;
	
	public SuppliesOrder() {}
	
	public SuppliesOrder( Supplies supply )
	{
		this.supply = supply;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Supplies getSupply() {
		return supply;
	}
	public void setSupply(Supplies supply) {
		this.supply = supply;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}