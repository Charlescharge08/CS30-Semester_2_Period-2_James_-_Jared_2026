import java.util.*;

public class Resources {
    private static Random rand = new Random();

    public static final String[] RESOURCES = {"Iron ore", "Copper Deposit", "Gold", "Water", "Uranium", "Food", "Alien Fossils", "Ancient Artifact", "Helium Gas", "Hydrogen Gas", "Rare Earth Elements"};
    
    public static List<String> planetResources(String Type){
        List<String> resources = new ArrayList<>();

        switch (Type.toLowerCase()){
            case "superearth":
                return superEarth();
            case "earth":
                return earth();
            case "subearth":
                return gasGiant();
            case "gas giant":
                return gasGiant();
            case "ice giant":
                return iceGiant();
        }
        return null;

    }

    private static List<String> superEarth(){
        List<String> r = new ArrayList<>();

        r.add("Iron Ore"); // Iron
        r.add("Water"); // Water
        r.add("Food");

        if (chance(70)){
             r.add("Copper Deposit");
        }
        if (chance(60)){
             r.add("Copper Deposit");
        }
        

        return r;
    }

    private static List<String> earth(){
    
    }

    private static List<String> subEarth(){
    
    }

    private static List<String> gasGiant(){
    
    }

    private static List<String> iceGiant(){
    
    }

     private static boolean chance(int percent) {
        return rand.nextInt(100) < percent;
    }

    


    
}
