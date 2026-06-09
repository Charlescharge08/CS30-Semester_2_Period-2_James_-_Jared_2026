public class CelestialBody extends Main{
    protected final double mass;
    protected final double radius;
    protected final String name;

    /**
     * Construct a celestial body with basic physical properties.
     * Mass value of the body
     * Radius value of the body
     */
    public CelestialBody(double mass, double radius, String name)
    {
        //if (mass > 1000)
        //{
        //    this.mass = ((int)(mass*100)) / 100;
        //}
        //else
        //{
            this.mass = mass;
        //}
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

    /**
     *Return a short description used when scanning without upgrades.
     *return brief scan text
     */
    public String basicScan()
    {
        return name + " is a " + mass + "Mj body with a radius of " + radius + "Rj.";
    }

    /**
     * Get the display name of this body.
     * return name string
     */
    public String getName()
    {
        return name;
    }
}
