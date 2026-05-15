import java.util.*;

public class Main {

    public static int[][][] map = new int[4][4][4];

    public static ArrayList<ArrayList<Star>> universe = new ArrayList<>();
    // universe represents each of the 125 different sectors as an index. Each of these indeces points to an arraylist of stars, around which the planets are orbiting

    public static void main(String[] args)
    {
        initialize();
        ArrayList<Star> systemSave = new ArrayList<>();
        ArrayList<Exoplanet> orbitingPlanets = new ArrayList<>();
        for (int i = 0; i < 64; i ++)
        {
            for (char n = 'A'; n <= 'Z'; n ++)
            {
                String name = n + "-" + (((int)n - (int)(Math.random() * 17))) + "." + i;
                System.out.println(name);
                double mass = getMass();
                String type = getType(mass);
                int planets = (int)(Math.random() * 8) + 1;
                Star saveStar = new Star(mass, Math.pow(mass, Math.pow(mass, 0.8)), name, type, getColour(type), planets);
                for (int j = 0; j < planets; j ++)
                {
                    double planetMass = Math.pow(10, (int)((Math.random()/5) - 1));
                    String planetName = name + (char)('A' + i);
                    Exoplanet planetSave = new Exoplanet(planetMass, Math.pow(planetMass, 0.8), planetName, getPlanetType(planetMass), (Math.random() > 0.99), getMoons(planetMass), (int)(Math.random()*512), getAtmosphere(planetMass), saveStar);
                    orbitingPlanets.add(planetSave);
                }
                saveStar.createSystem(orbitingPlanets);
                systemSave.add(saveStar);
            }
            universe.add(systemSave);
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
    }

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

}