import java.util.*;

public class Resources {
    private static Random rand = new Random();

    public static final String[] RESOURCES = {"Iron ore", "Copper Deposit", "Gold", "Water", "Uranium", "Food", "Alien Fossils", "Ancient Artifact", "Helium Gas", "Hydrogen Gas", "Rare Earth Elements"};
    
    public static List<String> planetResources(String Type){

        switch (Type.toLowerCase()){
            case "superearth":
                return superEarth();
            case "earth":
                return earth();
            case "subearth":
                return subEarth();
            case "gas giant":
                return gasGiant();
            case "ice giant":
                return iceGiant();
        }
        return null;

    }

    private static List<String> superEarth(){
        List<String> r = new ArrayList<>();

        r.add("Iron ore"); 
        r.add("Water");


        if (chance(70)){
             r.add("Copper Deposit");
        }
        if (chance(60)){
             r.add("Gold");
        }
        if(chance(50)){
            r.add("Uranium");
        }
        if (chance(40)){
             r.add("Rare Earth Elements");
        }
        if(chance(30)){
            r.add("Alien Fossils");
        }
        if (chance(20)){
             r.add("Ancient Artifact");
        }

        return r;
    }

    private static List<String> earth(){
        List<String> r = new ArrayList<>();
        r.add("Iron ore");
        r.add("Water"); 
        r.add("Food");

        if (chance(50)){
             r.add("Copper Deposit");
        }
        if (chance(35)){
             r.add("Gold");
        }
        if(chance(30)){
            r.add("Alien Fossils");
        }
        if (chance(20)){
            r.add("Ancient Artifact");
        }
        if(chance(15)){
            r.add("Rare Earth Elements");
        }

        return r;
    
    }

    private static List<String> subEarth(){
                List<String> r = new ArrayList<>();
        r.add("Iron ore"); 

        if (chance(70)){
             r.add("Water");
        }
        if (chance(60)){
             r.add("Copper Deposit");
        }

        return r;
    
    }

    private static List<String> gasGiant(){
                List<String> r = new ArrayList<>();
        r.add("Hydrogen Gas");
        r.add("Helium Gas"); 

        if (chance(15)){
             r.add("Ancient Artifact");
        }

        return r;
    
    }

    private static List<String> iceGiant(){
                List<String> r = new ArrayList<>();
        r.add("Water");
        r.add("Water");

        if (chance(70)){
             r.add("Helium Gas");
        }
        if (chance(60)){
             r.add("Alien Fossils");
        }
        if(chance(50)){
            r.add("Rare Earth Elements");
        }

        return r;
    
    }

     private static boolean chance(int percent) {
        return rand.nextInt(100) < percent;
    }
}
