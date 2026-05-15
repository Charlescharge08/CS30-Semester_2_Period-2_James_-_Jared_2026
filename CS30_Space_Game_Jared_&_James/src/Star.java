import java.util.*;

public class Star extends CelestialBody{
    private final String type;
    private final String colour;
    private final int planets;
    private final ArrayList<Exoplanet> system = new ArrayList<>();
    public Star(double mass, double radius, String name, String type, String colour, int planets)
    {
        super(mass, radius, name);
        this.type = type;
        this.colour = colour;
        this.planets = planets;
    }

    public String scan()
    {
        return name + " is a type " + type + " star with " + planets + " planets. It's surface is " + colour + ".";
    }

    public void createSystem(ArrayList<Exoplanet> input)
    {
        for (int i = 0; i < planets; i ++)
        {
            this.system.add(input.get(i));
        }
    }
}
