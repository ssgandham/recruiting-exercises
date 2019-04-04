
public class Inventory {
	String inventoryName;
	Long quantity;
	
	public Inventory() {
	}
	
	public Inventory(String inventoryName, Long quantity) {
		this.inventoryName = inventoryName;
		this.quantity = quantity;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
