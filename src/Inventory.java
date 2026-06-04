import java.util.*;
public class Inventory {
    private HashMap<String, Integer> items;


    public Inventory(){
        items = new HashMap<>();
    }
    
    /**
     * Add up to amount of item into the inventory, for cargo space.
     * If there is not enough space the method adds as much as possible.
     * item name
     * amount requested to add
     */
    public void addItem(String item, int amount){
        int cargoSpace = Main.playerShip.getCargoSpace();
        int usedSpace = getTotalItems();
        int openSpace = cargoSpace - usedSpace;

        if (openSpace <= 0) {
            System.out.print("Your cargo hold is full.");
            return;
        }

        int amountToAdd = Math.min(amount, openSpace);

        //If item already exsists
        if (items.containsKey(item)){
            int curentAmount = items.get(item);
            items.put(item, curentAmount + amountToAdd);
        }else{
            //add new item to inventory
            items.put(item, amountToAdd);
        }

        if (amountToAdd < amount) {
            System.out.print("Your cargo hold is full.");
        }
    }

    /**
     * Count total amount of all items in inventory.
     * return total item count
     */
    private int getTotalItems() {
        int total = 0;

        for (int amount : items.values()) {
            total += amount;
        }

        return total;
    }
    
    /**
     * Remove up to an amount of an item if its available.
     * item name of the item
     */
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

    /**
     * Get the stored amount of an item (0 if none).
     * item name
     * return quantity owned
     */
    public int getAmount(String item){
        
        if(items.containsKey(item)){
            return items.get(item);
        }
        return 0;
    }

    /**
     * Print the inventory summary.
     */
    public void printInventory(){
        System.out.println(getInventorySummary());
    }

    /**
     * Check whether the inventory has at least an amount of an item.
     * item name
     * amount needed quantity
     * return true when available
     */
    public boolean hasItem(String item, int amount) {
        return items.getOrDefault(item, 0) >= amount;
    }

     //return summary string
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

    // Sort an ArrayList of names using insertion sort.
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
