import java.util.*;

public class WareHouse {
	String wareHouseName;
	List<Inventory> listInventory = new LinkedList<>();
	
	public WareHouse() {
	}
	
	public WareHouse(String wareHouseName, List<Inventory> listInventory) {
		this.wareHouseName = wareHouseName;
		this.listInventory = listInventory;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public List<Inventory> getListInventory() {
		return listInventory;
	}

	public void setListInventory(List<Inventory> listInventory) {
		this.listInventory = listInventory;
	}

}
