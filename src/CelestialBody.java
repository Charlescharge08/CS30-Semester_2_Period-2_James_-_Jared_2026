public class CelestialBody extends Main{
    protected final double mass;
    protected final double radius;
    protected final String name;

    public CelestialBody(double mass, double radius, String name)
    {
        if (mass > 1000)
        {
            this.mass = ((int)(mass*100)) / 100;
        }
        else
        {
            this.mass = mass;
        }
        if (radius > 100)
        {
            this.radius = ((int)(radius*100)) / 100;
        }
        else
        {
            this.radius = radius;
        }
        this.name = name;
    }

    public String basicScan()
    {
        return name + " is a " + mass + "Mj body with a radius of " + radius + "Rj.";
    }

    public String getName()
    {
        return name;
    }
}
