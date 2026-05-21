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
        System.out.println("Inventory:");
        
        ArrayList<String> amount = new ArrayList<>(items.keySet());

        for (int i = 0; i < amount.size(); i++){
            String item = amount.get(i);
            System.out.println(item + " = " + items.get(item));
        }
    }

    public boolean hasItem(String item, int amount) {
        return items.getOrDefault(item, 0) >= amount;
    }
}
