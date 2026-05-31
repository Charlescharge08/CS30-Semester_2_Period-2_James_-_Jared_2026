public class Starship {
    private String shipName;

    private double fuel;
    private double maxFuel;

    private int cargoSpace;

    private int engineLevel;
    private int cargoLevel;
    private boolean scanUpgrade;

     public Starship(String name) {

        shipName = name;

        // Starting stats

        maxFuel = 500.0;
        fuel = 500.0;

        cargoSpace = 50;

        // Starting upgrades
        engineLevel = 1;
        cargoLevel = 1;
        scanUpgrade = false;
    }
    // did not have time to start on this and add to the string
    public String getStatus() {

        "Fuel: " + fuel + "/" + maxFuel + ". Cargo Space: " + cargoSpace + ".";
    }

    public String getName()
    {
        return shipName;
    }

    // did not have time to start on this and add to the string
    public String getUpgradeStatus() {



        return "Engine Level: " + engineLevel + ". Cargo Level: " + cargoLevel + ". scanUpgrade: " + scanUpgrade + ". Max Fuel: " + maxFuel + ".";
    }

    public void upgradeScan()
    {
        scanUpgrade = true;
    }


    public void upgradeEngine() {
        engineLevel++;
        maxFuel += 100;
    }

    public int getCargoSpace()
    {
        return cargoSpace;
    }

    public void upgradeCargo() {
        cargoLevel++;
        cargoSpace += 25;
    }

    public void useFuel(int amount) {

        fuel -= amount / engineLevel;

        if (Resources.chance(50 - (engineLevel*10)))
        {
            fuel -= amount / engineLevel;
            GameOutput.println("Your engine sputtered. Fuel loss doubles.");
        }
        

        if(fuel < 0) {
            fuel = 0;
        }
        
        GameOutput.println("Fuel used: " + amount + ". Remaining: " + fuel + "/" + maxFuel + ".");
    }

    public void refuel() {
        fuel = maxFuel;
    }

    public boolean getScanLevel()
    {
        return scanUpgrade;
    }

    public double getFuel()
    {
        return fuel;
    }
}

