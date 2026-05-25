public class Starship {
    private String shipName;

    private double fuel;
    private int maxFuel;

    private int cargoSpace;

    private int engineLevel;
    private int cargoLevel;
    private boolean scanUpgrade;

     public Starship(String name) {

        shipName = name;

        // Starting stats

        maxFuel = 500;
        fuel = 500;

        cargoSpace = 50;

        // Starting upgrades
        engineLevel = 1;
        cargoLevel = 1;
        scanUpgrade = false;
    }
    // did not have time to start on this and add to the string
    public String getStatus() {

        String output = "";

        return output;
    }

    // did not have time to start on this and add to the string
    public String getUpgradeStatus() {

        String output = "";



        return output;
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

        if(fuel < 0) {
            fuel = 0;
        }
    }

    public void refuel() {
        fuel = maxFuel;
    }

    public boolean getScanLevel()
    {
        return scanUpgrade;
    }
}

