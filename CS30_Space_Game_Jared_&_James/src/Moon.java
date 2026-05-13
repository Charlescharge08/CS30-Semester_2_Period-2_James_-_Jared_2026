public class Moon extends CelestialBody{
    private String type;
    private boolean hasLife;
    private double temperature;
    private String atmosphere;
    private Exoplanet parent;
    public Moon(double mass, double radius, String name, String type, boolean hasLife, double temperature, String atmosphere, Exoplanet parent)
    {
        super(mass, radius, name);
        this.type = type;
        this.hasLife=  hasLife;
        this.temperature = temperature;
        this.atmosphere = atmosphere;
        this.parent = parent;
    }

    public String scan()
    {
        return name + " is a " + type + " exoplanet orbiting " + parent.getName() + ". It's surface temperature is " + temperature + " kelvin and it has a " + atmosphere + " atmosphere. Has life: " + hasLife + ".";
    }
}
