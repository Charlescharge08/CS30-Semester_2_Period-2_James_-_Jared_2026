public class CelestialBody extends Main{
    protected double mass;
    protected double radius;
    protected String name;

    public CelestialBody(double mass, double radius, String name)
    {
        this.mass = mass;
        this.radius = radius;
        this.name = name;
    }

    public String basicScan()
    {
        return name + " is a " + mass + " ton body with a radius of " + radius + "km.";
    }

    public String getName()
    {
        return name;
    }
}
