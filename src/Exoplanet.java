public class Exoplanet extends CelestialBody{
    private final String type;
    private final boolean hasLife;
    private final int moons;
    private final double temperature;
    private final String atmosphere;
    private final Star parent;
    public Exoplanet(double mass, double radius, String name, String type, boolean hasLife, int moons, double temperature, String atmosphere, Star parent) {
        super(mass, radius, name);
        this.type = type;
        this.hasLife = hasLife;
        this.moons = moons;
        this.temperature = temperature;
        this.atmosphere = atmosphere;
        this.parent = parent;
    }
    
    public String scan()
    {
        return name + " is a " + type + " exoplanet with " + moons + " moons orbiting " + parent.getName() + ". It's surface temperature is " + temperature + " kelvin and it has a " + atmosphere + " atmosphere. Has life: " + hasLife + ".";
    }
}