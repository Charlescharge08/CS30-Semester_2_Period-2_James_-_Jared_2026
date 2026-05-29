public class Starship {
    private String shipName;

    private int hullHP;
    private int maxHullHP;

    private int fuel;
    private int maxFuel;

    private int cargoSpace;

    private int engineLevel;
    private int shieldLevel;
    private int weaponLevel;
    private int cargoLevel;

     public Starship(String name) {

        shipName = name;

        // Starting stats
        maxHullHP = 100;
        hullHP = 100;

        maxFuel = 500;
        fuel = 500;

        cargoSpace = 50;

        // Starting upgrades
        engineLevel = 1;
        shieldLevel = 1;
        weaponLevel = 1;
        cargoLevel = 1;
    }

    // did not have time to start on this and add to the string
    public String getStatus() {
        return "Ship Name: " + shipName + "\n" + "Hull: " + hullHP + "/" + maxHullHP + "\n" + "Fuel: " + fuel + "/" + maxFuel + "\n" + "Cargo Space: " + cargoSpace;
    }

    // did not have time to start on this and add to the string
    public String getUpgradeStatus() {
        return "Engine Level: " + engineLevel + "\n" + "Shield Level: " + shieldLevel + "\n" + "Weapon Level: " + weaponLevel + "\n" + "Cargo Level: " + cargoLevel;
    }


    public void upgradeEngine() {
        engineLevel++;
        maxFuel += 100;
    }

    public void upgradeShields() {
        shieldLevel++;
        maxHullHP += 50;
        hullHP = maxHullHP;
    }

    public void upgradeWeapons() {
        weaponLevel++;
    }

    public void upgradeCargo() {
        cargoLevel++;
        cargoSpace += 25;
    }

    public void takeDamage(int damage) {

        hullHP -= damage;

        if(hullHP < 0) {
            hullHP = 0;
        }
    }

    public void useFuel(int amount) {

        fuel -= amount;

        if(fuel < 0) {
            fuel = 0;
        }
    }

    public void refuel() {
        fuel = maxFuel;
    }
}

