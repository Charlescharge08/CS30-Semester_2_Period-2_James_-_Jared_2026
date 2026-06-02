import com.googlecode.lanterna.screen.Screen;   
import java.util.*;
public class Main {
    public static int[][][] map = new int[4][4][4];

    public static int playerX;
    public static int playerY;
    public static int playerZ;

    public static Starship playerShip = new Starship("Excellence");
    public static Inventory inventory = new Inventory();
    
    public static Terminal_Oppener opener = new Terminal_Oppener();

    public static ArrayList<ArrayList<Star>> universe = new ArrayList<>();

    public static Screen screen = opener.openTerminal();
    
    public static GameConsole gameConsole = new GameConsole();

    public static LanternaMenuEngine menu = new LanternaMenuEngine(screen, playerShip, inventory);
    public static void main(String[] args) throws Exception {
        GameOutput.initialize(gameConsole, menu);
        generateUniverse();
        GameOutput.println("You are the final member of humanity after the explosion of the sun. \nYou have lived your whole life in space with your parents, \nlearning how to live off of the various planets and stars around the galaxy. \nNow, they have died, and left you alone with only their ship, the Excellence. \nYou must survive by yourself in the cold galaxy. Your goal is to find \na nice habitable planet somewhere in the galaxy, and settle down.\r\n");
        GameOutput.println("Welcome to the Excellence. This is your lifeline. Choose wisely.");
        menu.startMainMenu();
        System.exit(0);
    }

    public static void generateUniverse(){
        ArrayList<Star> systemSave = new ArrayList<>();
        ArrayList<Exoplanet> orbitingPlanets = new ArrayList<>();
        for (int i = 0; i < 64; i ++)
        {
            for (char n = 'A'; n <= 'G'; n ++)
            {
                String name = n + "-" + (((int)n - (int)(Math.random() * 17))) + "." + i;
                double mass = getMass();
                String type = getType(mass);
                int planets = (int)(Math.random() * 8) + 1;
                Star saveStar = new Star(mass, Math.pow(mass,0.8), name, type, getColour(type), planets);
                for (int j = 0; j < planets; j ++)
                {
                    double planetMass = Math.pow(10, (int)(-(Math.random()*6)) - 1);
                    String planetName = name + "-" + (char)('A' + j);
                    String planetType = getPlanetType(planetMass);
                    String planetAtmosphere = getAtmosphere(planetMass);
                    int planetTemperature = (int)(Math.random()*512);
                    boolean planetLife = getLife(planetType, planetAtmosphere, planetTemperature);
                    ArrayList<String> resources = new ArrayList<>(Resources.planetResources(planetType, planetLife));
                    Exoplanet planetSave = new Exoplanet(planetMass, Math.pow(planetMass, 0.8), planetName, planetType, planetLife, getMoons(planetMass), planetTemperature, planetAtmosphere, saveStar, new ArrayList<>(resources));
                    orbitingPlanets.add(planetSave);
                    resources.clear();
                }
                saveStar.createSystem(new ArrayList<>(orbitingPlanets));
                orbitingPlanets.clear();
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

        return type + (decimal + "").substring(0, 3); // If possible, fix rounding
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

    public static boolean getLife(String type, String atmosphere, int temperature)
    {
        if ((223 < temperature && temperature < 323) && atmosphere.equals("Medium") && type.equals("Rocky") || type.equals("Superrocky") || type.equals("Subrocky"))
        {
            return Resources.chance(20);
        }
        else
        {
            return Resources.chance(1);
        }
    }
    // end getLife

    public static String surroundings()
    {
        String surroundings = "";
        int position = getPosition();
        if (playerShip.getScanLevel())
        {
            for (int i = 0; i < 7; i ++)
            {
                surroundings += universe.get(position).get(i).scan();
                surroundings += "\n";
            }
        }
        else
        {
            for (int i = 0; i < 7; i ++)
            {
                surroundings += universe.get(position).get(i).basicScan();
                surroundings += "\n";
            }
        }
        
        return surroundings;
    }
    // end surroundings

    public static int getPosition()
    {
        return (playerX + 4*playerY + 16*playerZ);
    }
    // end getPosition

    public static boolean movement(int moveType)
    {
        if (playerShip.getFuel() <= 100.0)
        {
            GameOutput.println("Not enough fuel.");
            return false;
        }
        
        if (moveType == 0)
        {
            if (playerX != 3)
            {
                playerX++;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 1)
        {
            if (playerX != 0)
            {
                playerX--;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 2)
        {
            if (playerY != 3)
            {
                playerY++;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 3)
        {
            if (playerY != 0)
            {
                playerY--;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 4)
        {
            if (playerZ != 3)
            {
                playerZ++;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 5)
        {
            if (playerZ != 0)
            {
                playerZ--;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        playerShip.useFuel(100);
        return true;
    }
    // end movement

    public static ArrayList<Star> getSector()
    {
        return universe.get(getPosition());
    }
    // end getSector

    public static Star choiceStar;
    public static Exoplanet choicePlanet;

    public static void setChoiceStar(Star input)
    {
        if (choiceStar != input)
        {
            choicePlanet = null;
        }

        choiceStar = input;
    }
    // end setChoiceStar

    public static Star getChoiceStar()
    {
        return choiceStar;
    }
    // end getChoiceStar

    public static void setChoicePlanet(Exoplanet input)
    {
        choicePlanet = input;
    }
    // end setChoicePlanet

    public static Exoplanet getChoicePlanet()
    {
        return choicePlanet;
    }
    // end getChoicePlanet

    public static int playerLandX;
    public static int playerLandY;
    public static String[][] land;

    public static void landing(String type, Exoplanet input)
    {
        if (type.equals("Superrocky"))
        {
            land = new String[5][5];
            
        }
        else if (type.equals("Rocky"))
        {
            land = new String[4][4];
        }
        else
        {
            land = new String[3][3];
        }
        ArrayList<String> resources = new ArrayList<String>(input.getResources());

        for (int i = 0; i < land.length; i ++)
        {
            for (int n = 0; n < land.length; n ++)
            {
                if (Resources.chance(25) && !resources.isEmpty())
                {
                    land[i][n] = resources.get(0);
                    resources.remove(0);
                }
                else
                {
                    land[i][n] = "-";
                }
            }
        }
        playerLandX = 0;
        playerLandY = 0;

        input.modifyResources(new ArrayList<String>(resources));
    }
    // end landing

    public static void landingMovement(int status)
    {
        if (status == 0)
        {
            playerLandX ++;
            if (playerLandX == land.length)
            {
                playerLandX = 0;
            }
        }
        else if (status == 1)
        {
            playerLandX --;
            if (playerLandX < 0)
            {
                playerLandX = land.length - 1;
            }
        }
        else if (status == 0)
        {
            playerLandX ++;
            if (playerLandX == land.length)
            {
                playerLandX = 0;
            }
        }
        else if (status == 1)
        {
            playerLandX --;
            if (playerLandX < 0)
            {
                playerLandX = land.length;
            }
        }
        else if (status == 2)
        {
            playerLandY ++;
            if (playerLandY == land.length)
            {
                playerLandY = 0;
            }
        }
        else if (status == 3)
        {
            playerLandY --;
            if (playerLandY < 0)
            {
                playerLandY = land.length - 1;
            }
        }

        if (!land[playerLandX][playerLandY].equals("-"))
        {
            int amount = (int)(Math.random()*2) + 1;

            inventory.addItem(land[playerLandX][playerLandY], amount);

            GameOutput.println("You found " + amount + " units of " + land[playerLandX][playerLandY] + "!");

            land[playerLandX][playerLandY] = "-";
        }
    }
    // end landingMovement

    public static String getLandingPosition()
    {
        return "Your position:" + playerLandX + ", " + playerLandY;
    }
    // end getLandingPosition
}
// end Main
