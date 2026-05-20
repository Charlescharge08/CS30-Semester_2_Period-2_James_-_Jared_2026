public class Star extends CelestialBody{
    private String type;
    private String colour;
    private int planets;
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
}
