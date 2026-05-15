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
            }
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