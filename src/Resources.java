import java.util.*;

public class Resources {
    private static Random rand = new Random();

    public static final String[] RESOURCES = {"Iron ore", "Copper", "Gold", "Water", "Uranium", "Food", "Alien Fossils", "Ancient Artifact", "Helium Gas", "Hydrogen Gas", "Rare rocky Elements", "Weird Alien Carcass"};
    
    /**
     * Generate a list of possible resources for a planet type.
     * Type planet type string
     * whether the planet has life
     * return list of resource 
     */
    public static List<String> planetResources(String Type, boolean hasLife){

        List<String> returnValues = null;
        
        switch (Type.toLowerCase()){
            case "superrocky":
                returnValues = superrocky();
                break;
            case "rocky":
                returnValues = rocky();
                break;
            case "subrocky":
                returnValues = subrocky();
                break;
            case "gas giant":
                returnValues = gasGiant();
                break;
            case "ice giant":
                returnValues = iceGiant();
                break;
            default:
                returnValues = new ArrayList<>();
        }

        if (hasLife)
        {
            returnValues.add("Weird Alien Carcass");
        }
        
        return returnValues;

    }

    private static List<String> superrocky(){
        List<String> r = new ArrayList<>();

        r.add("Iron ore");
        r.add("Copper");


        if (chance(70)){
             r.add("Copper");
        }
        if (chance(60)){
             r.add("Gold");
        }
        if(chance(50)){
            r.add("Uranium");
        }
        if (chance(40)){
             r.add("Rare rocky Elements");
        }
        if(chance(30)){
            r.add("Alien Fossils");
        }
        if (chance(20)){
             r.add("Ancient Artifact");
        }

        return r;
    }

    private static List<String> rocky(){
        List<String> r = new ArrayList<>();
        r.add("Iron ore");
        r.add("Copper");
        r.add("Uranium");

        if (chance(50)){
             r.add("Copper");
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
            r.add("Rare rocky Elements");
        }

        return r;
    
    }

    private static List<String> subrocky(){
        List<String> r = new ArrayList<>();
        r.add("Iron ore"); 
        r.add("Copper");

        if (chance(70)){
             r.add("Water");
        }
        if (chance(60)){
             r.add("Copper");
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

        if (chance(70)){
             r.add("Helium Gas");
        }
        if (chance(60)){
             r.add("Alien Fossils");
        }
        if(chance(50)){
            r.add("Rare rocky Elements");
        }

        return r;
    
    }

     public static boolean chance(int percent) {
        return rand.nextInt(100) < percent;
    }
}
