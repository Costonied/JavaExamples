/**
 * Get examples from https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
 */

// Java Code to sort Map by key value 
import java.util.*; 

class SortingHashMapAccordingKeys { 

	// This map stores unsorted values 
	static Map<String, Integer> map = new HashMap<>(); 

	// Function to sort map by Key using TreeMap (putAll method)
	public static void sortbykeyUsingTreeMapPutAll() 
	{ 
		// TreeMap to store values of HashMap 
		TreeMap<String, Integer> sorted = new TreeMap<>(); 

		// Copy all data from hashMap into TreeMap 
		sorted.putAll(map); 

		// Display the TreeMap which is naturally sorted 
		for (Map.Entry<String, Integer> entry : sorted.entrySet()) 
			System.out.println("Key = " + entry.getKey() + 
						", Value = " + entry.getValue());		 
	}
    
    // Function to sort map by Key TreeMap (Constructor)
    public static void sortbykeyUsingTreeMapConstructor() 
    { 
        // TreeMap to store values of HashMap 
        TreeMap<String, Integer> sorted = new TreeMap<>(map); 
  
        // Display the TreeMap which is naturally sorted 
        for (Map.Entry<String, Integer> entry : sorted.entrySet())  
            System.out.println("Key = " + entry.getKey() +  
                         ", Value = " + entry.getValue());         
    }
    
    // Function to sort map by Key using ArrayList
    public static void sortbykeyUsingArrayList()
    { 
        ArrayList<String> sortedKeys = 
                    new ArrayList<String>(map.keySet()); 
          
        Collections.sort(sortedKeys);  
  
        // Display the TreeMap which is naturally sorted 
        for (String x : sortedKeys)  
            System.out.println("Key = " + x +  
                        ", Value = " + map.get(x));      
    }
	
	// Driver Code 
	public static void main(String args[]) 
	{ 
		// putting values in the Map 
		map.put("Jayant", 80); 
		map.put("Abhishek", 90); 
		map.put("Anushka", 80); 
		map.put("Amit", 75); 
		map.put("Danish", 40); 

		// Calling the functions to sortbyKey
		sortbykeyUsingTreeMapPutAll();
        sortbykeyUsingTreeMapConstructor();
        sortbykeyUsingArrayList();
	} 
} 
