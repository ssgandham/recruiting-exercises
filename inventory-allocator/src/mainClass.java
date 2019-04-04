import java.util.*;

public class mainClass {
	static List<OutPut> finalResult = new LinkedList<>();

	private static boolean checkIfAllOrdersProcessed(Map<String, Long> mapOrders) {
		for (Long tmpKeyValue : mapOrders.values()) {
			if (tmpKeyValue > 0)
				return false;
		}
		return true;
	}

	private static boolean removeFromOrderMap(WareHouse tmpWareHouse, Map<String, Long> mapOrders,
			List<OutPut> result) {
		List<String> tmpList = new LinkedList<>();
		for (Inventory tmpInventory : tmpWareHouse.listInventory) {
			String tmpInventoryName = tmpInventory.getInventoryName();
			Long tmpInventoryQuantity = tmpInventory.getQuantity();
			if (mapOrders.getOrDefault(tmpInventoryName, (long) -1) > 0) {
				tmpList.add(tmpInventoryName + ":" + Math.min(tmpInventoryQuantity, mapOrders.get(tmpInventoryName)));
				mapOrders.put(tmpInventoryName, mapOrders.get(tmpInventoryName) - tmpInventoryQuantity);
			}
		}

		if (tmpList.size() > 0) {
			result.add(new OutPut(tmpWareHouse.wareHouseName, tmpList));
			return true;
		}
		return false;
	}

	private static void findShortestPath(Map<String, Long> mapOrders, List<WareHouse> listInputWareHouses,
			List<OutPut> result, int index, Long path,Long maxPath) {
		if (mapOrders == null || listInputWareHouses == null || mapOrders.size() == 0 || listInputWareHouses.size() == 0)
			return;

		if (checkIfAllOrdersProcessed(mapOrders) && path < maxPath) {
			maxPath = path;
			finalResult = new LinkedList<OutPut>(result);
		} else {

			Map<String, Long> mapOrdersMain = mapOrders;
			List<OutPut> resultMain = result;
			for (int i = index; i < listInputWareHouses.size(); i++) {
				WareHouse tmpWareHouse = listInputWareHouses.get(i);
				removeFromOrderMap(tmpWareHouse, mapOrders, result);
				findShortestPath(mapOrders, listInputWareHouses, result, index + 1, path + 1,maxPath);
				mapOrders = mapOrdersMain;
				result = resultMain;
			}
		}
	}

	private static void printResult(List<OutPut> result) {
		if (result.size() == 0) {
			System.out.print("Output: [ Not enough inventory -> no allocations!");
		} else {
			System.out.print("Output:[");
			for (OutPut tmpOut : finalResult) {
				System.out.print("{ " + tmpOut.wareHouseName + ": {");
				for (int i = 0; i < tmpOut.listInventory.size(); i++) {
					String str = tmpOut.listInventory.get(i);
					if (i == tmpOut.listInventory.size() - 1)
						System.out.print(str);
					else
						System.out.print(str + ",");
				}
				System.out.print("}}");
			}

		}
		System.out.print(" ]");
	}

	public static void main(String[] args) {
		Map<String, Long> mapOrders = new LinkedHashMap<>();

//		Test 1
		System.out.println("Test 1");
		System.out.println("Input : "
				+ "{ apple: 5, banana: 5, orange: 5 }, [ { name: owd, inventory: { apple: 5, orange: 10 } }, { name: dm:, inventory: { banana: 5, orange: 10 } } ]");
		mapOrders.put("apple", (long) 5);
		mapOrders.put("orange", (long) 5);
		mapOrders.put("banana", (long) 5);

		List<Inventory> listInventories = new LinkedList<>();
		List<WareHouse> listInputWareHouses = new LinkedList<>();

		listInventories.add(new Inventory("apple", (long) 5));
		listInventories.add(new Inventory("orange", (long) 10));

		listInputWareHouses.add(new WareHouse("owd", listInventories));

		List<Inventory> listInventories1 = new LinkedList<>();
		listInventories1.add(new Inventory("apple", (long) 1));
		listInventories1.add(new Inventory("banana", (long) 10));

		listInputWareHouses.add(new WareHouse("dm", listInventories1));

		List<Inventory> listInventories2 = new LinkedList<>();
		listInventories1.add(new Inventory("apple", (long) 10));
		listInventories1.add(new Inventory("banana", (long) 10));

		listInputWareHouses.add(new WareHouse("dm2", listInventories2));

		findShortestPath(mapOrders, listInputWareHouses, new LinkedList<OutPut>(), 0, (long) 0,Long.MAX_VALUE);
		printResult(finalResult);

//		Test 2
		System.out.println("\n");
		System.out.println("Test 2");
		System.out.println("Input: { apple: 1 }, [{ name: owd, inventory: { apple: 0 } }]");
		mapOrders.clear();
		listInputWareHouses.clear();
		listInventories.clear();
		finalResult.clear();

		mapOrders.put("apple", (long) 1);
		listInventories.add(new Inventory("apple", (long) 0));
		listInputWareHouses.add(new WareHouse("owd", listInventories));
		findShortestPath(mapOrders, listInputWareHouses, new LinkedList<OutPut>(), 0, (long) 0,Long.MAX_VALUE);
		printResult(finalResult);

//		Test 3
		System.out.println("\n");
		System.out.println("Test 3");
		System.out.println("Input: {},[]");
		mapOrders.clear();
		listInputWareHouses.clear();
		listInventories.clear();
		finalResult.clear();
		findShortestPath(mapOrders, listInputWareHouses, new LinkedList<OutPut>(), 0, (long) 0,Long.MAX_VALUE);
		printResult(finalResult);

//		Test 4
		System.out.println("\n");
		System.out.println("Test 4");
		System.out.println("Input: { apple: 1 }, [{ name: owd, inventory: { apple: 1 } }]");
		mapOrders.clear();
		listInputWareHouses.clear();
		listInventories.clear();
		finalResult.clear();

		mapOrders.put("apple", (long) 1);
		listInventories.add(new Inventory("apple", (long) 1));
		listInputWareHouses.add(new WareHouse("owd", listInventories));
		findShortestPath(mapOrders, listInputWareHouses, new LinkedList<OutPut>(), 0, (long) 0,Long.MAX_VALUE);
		printResult(finalResult);

//		Test 5
		System.out.println("\n");
		System.out.println("Test 5");
		System.out.println("Input: { apple: 10 }, [{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 10 }}]");
		mapOrders.clear();
		listInputWareHouses.clear();
		listInventories.clear();
		finalResult.clear();

		mapOrders.put("apple", (long) 10);
		listInventories.add(new Inventory("apple", (long) 5));
		listInputWareHouses.add(new WareHouse("owd", listInventories));

		listInventories.clear();
		listInventories.add(new Inventory("apple", (long) 10));

		listInputWareHouses.add(new WareHouse("dm", listInventories));

		findShortestPath(mapOrders, listInputWareHouses, new LinkedList<OutPut>(), 0, (long) 0,Long.MAX_VALUE);
		printResult(finalResult);

//		Test 6
		System.out.println("\n");
		System.out.println("Test 6");
		System.out.println("Input: { apple: 10 }, [{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 5 }}]");
		mapOrders.clear();
		listInputWareHouses.clear();
		listInventories.clear();
		finalResult.clear();

		mapOrders.put("apple", (long) 10);
		listInventories.add(new Inventory("apple", (long) 5));
		listInputWareHouses.add(new WareHouse("owd", listInventories));

		listInventories.clear();
		listInventories.add(new Inventory("apple", (long) 5));

		listInputWareHouses.add(new WareHouse("dm", listInventories));

		findShortestPath(mapOrders, listInputWareHouses, new LinkedList<OutPut>(), 0, (long) 0,Long.MAX_VALUE);
		printResult(finalResult);

	}
}
