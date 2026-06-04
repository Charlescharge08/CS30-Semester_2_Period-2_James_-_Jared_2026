import java.util.ArrayList;

public class Exoplanet extends CelestialBody{
    private final String type;
    private final boolean hasLife;
    private final int moons;
    private final double temperature;
    private final String atmosphere;
    private final Star parent;
    private ArrayList<String> resources = new ArrayList<>();
    public Exoplanet(double mass, double radius, String name, String type, boolean hasLife, int moons, double temperature, String atmosphere, Star parent, ArrayList<String> resources) {
        super(mass, radius, name);
        this.type = type;
        this.hasLife = hasLife;
        this.moons = moons;
        this.temperature = temperature;
        this.atmosphere = atmosphere;
        this.parent = parent;
        this.resources = new ArrayList<String>(resources);
    }
    
    public String scan()
    {
        return name + " is a " + type + " exoplanet with " + moons + " moons orbiting " + parent.getName() + ".\nIt's surface temperature is " + temperature + " kelvin, \nand it has a " + atmosphere + " atmosphere.\nHas life: " + hasLife + ".";
    }

    public ArrayList<String> getResources()
    {
        return resources;
    }

    public String type()
    {
        return type;
    }

    public void modifyResources(ArrayList<String> input)
    {
        resources = new ArrayList<String>(input);
    }
}