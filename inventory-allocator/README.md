There are different classes which has been created
```
//This class stores the inventory name and quantity

public class Inventory {
	String inventoryName;
	Long quantity;
}

//This class is for storing the output of warehouse name and inventory lists
public class OutPut {
	String wareHouseName;
	List<String> listInventory;
}

//This class is for storing the warehouse name and list of inventories
public class WareHouse {
	String wareHouseName;
	List<Inventory> listInventory = new LinkedList<>();
}
```
The mainClass.java has the whole code along with the test cases. Compile and run the mainClass.java which will display the input and output as follows.

Test 1
Input : { apple: 5, banana: 5, orange: 5 }, [ { name: owd, inventory: { apple: 5, orange: 10 } }, { name: dm:, inventory: { banana: 5, orange: 10 } } ]
Output:[{ owd: {apple:5,orange:5}}{ dm: {banana:5}} ]

Test 2
Input: { apple: 1 }, [{ name: owd, inventory: { apple: 0 } }]
Output: [ Not enough inventory -> no allocations! ]

Test 3
Input: {},[]
Output: [ Not enough inventory -> no allocations! ]

Test 4
Input: { apple: 1 }, [{ name: owd, inventory: { apple: 1 } }]
Output:[{ owd: {apple:1}} ]

Test 5
Input: { apple: 10 }, [{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 10 }}]
Output:[{ owd: {apple:10}} ]

Test 5
Input: { apple: 10 }, [{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 5 }}]
Output:[{ owd: {apple:5}}{ dm: {apple:5}} ]
