import com.googlecode.lanterna.screen.Screen;   
import java.util.*;
public class Main {
    public static int[][][] map = new int[4][4][4];

    public static int playerX;
    public static int playerY;
    public static int playerZ;

    public static ArrayList<ArrayList<Star>> universe = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Terminal_Oppener opener = new Terminal_Oppener();
        Screen screen = opener.openTerminal();
        Starship playerShip = new Starship("IDK what to name it yet");
        Inventory inventory = new Inventory();
        LanternaMenuEngine menu = new LanternaMenuEngine(screen, playerShip, inventory);
        
        GameConsole gameConsole = new GameConsole();
        
        GameOutput.initialize(gameConsole, menu);

        
        
        GameOutput.println("Game started!");
        GameOutput.println("Welcome to STELLAR TERMINAL");
        generateUniverse();
        GameOutput.println("Universe generated with " + universe.size() + " sectors!");
        
        menu.startMainMenu();
        System.exit(0);

    }

    public static void generateUniverse(){
        ArrayList<Star> systemSave = new ArrayList<>();
        ArrayList<Exoplanet> orbitingPlanets = new ArrayList<>();
        for (int i = 0; i < 64; i ++)
        {
            for (char n = 'A'; n <= 'Z'; n ++)
            {
                String name = n + "-" + (((int)n - (int)(Math.random() * 17))) + "." + i;
                double mass = getMass();
                String type = getType(mass);
                int planets = (int)(Math.random() * 8) + 1;
                Star saveStar = new Star(mass, Math.pow(mass, Math.pow(mass, 0.8)), name, type, getColour(type), planets);
                for (int j = 0; j < planets; j ++)
                {
                    double planetMass = Math.pow(10, (int)(-(Math.random()*6)) - 1);
                    String planetName = name + " " + (char)('A'+ i);
                    String planetType = getPlanetType(planetMass);
                    String planetAtmosphere = getAtmosphere(planetMass);
                    Exoplanet planetSave = new Exoplanet(planetMass, Math.pow(planetMass, 0.8), planetName, planetType, getLife(planetType, planetAtmosphere), getMoons(planetMass), (int)(Math.random()*512), planetAtmosphere, saveStar);
                    orbitingPlanets.add(planetSave);
                }
                saveStar.createSystem(orbitingPlanets);
                systemSave.add(saveStar);
            }
            universe.add(new ArrayList<>(systemSave));
            systemSave.clear();
        }
    }
        

    public static void initialize()
    {
        for (int i = 0; i < map.length; i ++)
        {
            for (int n = 0; n < map.length; n ++)
            {
                for (int j = 0; j < map.length; j ++)
                {
                    map[i][n][j] = j + (4
                        *n) + (16*i);
                }
            }
        }

        playerX = 0;
        playerY = 0;
        playerZ = 0;
    }
    // end initialize

    public static void printMap()
    {
        for (int i = 0; i < map.length; i ++)
            {
                System.out.println("Row " + (i + 1) + ":");
                for (int n = 0; n < map.length; n ++)
                {
                    System.out.println("Box " + (n + 1) + ":");
                    for (int j = 0; j < map.length; j ++)
                    {
                        System.out.print(" " + map[i][n][j] + " ");
                    }
                    System.out.println();
                }
            }
    }
    // end printMap

    public static double getMass()
    {
        double seed = Math.random();
        return (Math.pow(seed, 4)*100000) + 80.0;
        // second int in Math.pow reflects the distribution of masses. Higher numbers lead to more light stars on average, while lower numbers lead to more consistent distribution of stars
    }
    // end getMass

    public static String getType(double mass)
    {
        String type = "";
        double decimal;
        if (mass < 700)
        {
            type += "M";

            decimal = (mass / 700) * 10;
        }
        else if (mass < 840)
        {
            type += "K";
            decimal = ((mass - 700) / 140) * 10;
        }
        else if (mass < 1090)
        {
            type += "G";
            decimal = ((mass - 840) / 250) * 10;
        }
        else if (mass < 1470)
        {
            type += "F";
            decimal = ((mass - 1090) / 380) * 10;
        }
        else if (mass < 2200)
        {
            type += "A";
            decimal = ((mass - 1470) / 730) * 10;
        }
        else if (mass < 16700)
        {
            type += "B";
            decimal = ((mass - 2200) / 14500) * 10;
        }
        else
        {
            type += "O";
            decimal = ((mass - 16700) / 83300) * 10;
        }

        decimal = Math.round(decimal*10)/10;
        return type + decimal;
    }
    // end getType

    public static String getColour(String type)
    {
        String letterType = type.substring(0, 1);

        if (letterType.equals("O"))
        {
            return "Violet-White";
        }
        else if (letterType.equals("B"))
        {
            return "Blue-White";
        }
        else if (letterType.equals("A"))
        {
            return "White";
        }
        else if (letterType.equals("F"))
        {
            return "Yellow-White";
        }
        else if (letterType.equals("G"))
        {
            return "Yellow";
        }
        else if (letterType.equals("K"))
        {
            return "Orange";
        }
        else
        {
            return "Red";
        }
    }
    // end getColour

    public static String getPlanetType(double mass)
    {
        if (mass > 0.3)
        {
            return "Gas Giant";
        }
        else if (mass > 0.03)
        {
            return "Ice Giant";
        }
        else if (mass > 0.003)
        {
            return "Superrocky";
        }
        else if (mass > 0.0003)
        {
            return "Rocky";
        }
        else
        {
            return "Subrocky";
        }
    }
    // end getPlanetType

    public static int getMoons(double mass)
    {
        if (mass > 0.03)
        {
            return (int)(Math.random()*10);
        }
        else
        {
            return (int)(Math.random()*3);
        }
    }
    // end getMoons

    public static String getAtmosphere(double mass)
    {
        if (mass > 0.03)
        {
            return "Incredibly High";
        }
        else
        {
            double randomAtmosphere = Math.pow(Math.random(), 3);
            if (randomAtmosphere > 0.8)
            {
                return "Incredibly High";
            }
            else if (randomAtmosphere > 0.6)
            {
                return "High";
            }
            else if (randomAtmosphere > 0.4)
            {
                return "Medium";
            }
            else if (randomAtmosphere > 0.2)
            {
                return "Minimal";
            }
            else
            {
                return "Incredibly Minimal";
            }
        }
    }
    // end getAtmosphere

    public static boolean getLife(String type, String atmopshere)
    {
        if (type.equals("Gas Giant") || type.equals("Ice Giant"))
        {
            return Math.random() > 0.98;
            // replace with James' function later
        }
        else if (type.equals("Superrocky") || type.equals("Rocky") && (atmopshere.equals("High") || atmopshere.equals("Medium")))
        {
            return Math.random() > 0.90;
        }
        else
        {
            return false;
        }
    }
    // end getLife
}
// end Main
