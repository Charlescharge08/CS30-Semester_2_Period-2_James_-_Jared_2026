import java.util.*;
public class Inventory {
    private HashMap<String, Integer> items;


    //The constructor 

    public Inventory(){
        items = new HashMap<>();
    }
    
    public void addItem(String item, int amount){
        //If item already exsists
        if (items.containsKey(item)){
            int curentAmount = items.get(item);
            items.put(item, curentAmount + amount);
        }else{
            //add new item to inventory
            items.put(item, amount);
        }
    }
    
   //Remove items
   public void removeItems(String item, int removeAmount){
        if(items.containsKey(item)){
            if (items.get(item) >= removeAmount) {
                items.put(item, items.get(item) - removeAmount); 
            
                if(items.get(item) <= 0){
                    items.remove(item);
                }
            }else{
                System.out.print("You do not have enough " + item + " in your inventory.");
            }        
        }
        
    }

    //get the amount of item
    public int getAmount(String item){
        
        if(items.containsKey(item)){
            return items.get(item);
        }
        return 0;
    }

    // print invitory
    public void printInventory(){
        System.out.println(getInventorySummary());
    }

    public boolean hasItem(String item, int amount) {
        return items.getOrDefault(item, 0) >= amount;
    }

    public String getInventorySummary() {
        StringBuilder summary = new StringBuilder("Inventory:");

        ArrayList<String> itemNames = new ArrayList<>();
        for (String item : items.keySet()) {
            if (items.get(item) > 0) {
                itemNames.add(item);
            }
        }

        if (itemNames.isEmpty()) {
            summary.append("\n(empty)");
            return summary.toString();
        }

        insertionSortAlphabetical(itemNames);

        for (int i = 0; i < itemNames.size(); i++) {
            String item = itemNames.get(i);
            summary.append("\n").append(item).append(" = ").append(items.get(item));
        }

        return summary.toString();
    }

    private void insertionSortAlphabetical(ArrayList<String> itemNames) {
        for (int i = 1; i < itemNames.size(); i++) {
            String current = itemNames.get(i);
            int j = i - 1;

            while (j >= 0 && compareAlphabetical(itemNames.get(j), current) > 0) {
                itemNames.set(j + 1, itemNames.get(j));
                j--;
            }

            itemNames.set(j + 1, current);
        }
    }

    private int compareAlphabetical(String left, String right) {
        int compareIgnoreCase = left.compareToIgnoreCase(right);
        if (compareIgnoreCase != 0) {
            return compareIgnoreCase;
        }

        return left.compareTo(right);
    }
}
