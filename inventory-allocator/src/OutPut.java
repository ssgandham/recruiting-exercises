import java.util.*;

public class OutPut {
	String wareHouseName;
	List<String> listInventory;

	public OutPut(String wareHouseName, List<String> listInventory) {
		this.wareHouseName = wareHouseName;
		this.listInventory = listInventory;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public List<String> getListInventory() {
		return listInventory;
	}

	public void setListInventory(List<String> listInventory) {
		this.listInventory = listInventory;
	}

}
