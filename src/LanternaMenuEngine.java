import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class LanternaMenuEngine {
    private enum MenuChoice {
        MAIN,
        NAVIGATION,
        MOVEMENT,
        SHIP,
        INVENTORY,
        CRAFTING,
        LISTSTARS,
        LISTPLANETS,
        PLANETOPTIONS,
        EXIT
    }

    private final ArrayList<String> consoleLines = new ArrayList<>();
    private final Screen screen;
    private final Starship playerShip;
    private final Inventory inventory;
    private final MultiWindowTextGUI gui;
    private TextBox consoleBox;
    private final String[] craftingRecipes = {
        "Copper Wire (Copper Deposit x2)",
        "Fuel Cell (Hydrogen Gas x5, Helium Gas x1)",
        "Iron Mesh (Iron ore x2)",
        "Advanced Fuel Cell (Uranium x1)",
        "Advanced Info-Grabber (Uranium x2, Ancient Artifact x1)",
        "Scanner Upgrade (Advanced Info-Grabber x1, Copper Wire x5)",
        "Engine Upgrade (Rare rocky Elements x3, Uranium x2, Iron ore x8, Copper Deposit x3, Copper Wire x3, Advanced Fuel Cell x1)",
        "Cargo Space (Iron Mesh x2, Alien Fossils x3)"
    };

    private class DevWindow extends BasicWindow {
        DevWindow(String title) {
            super(title);
        }

        @Override
        public boolean handleInput(KeyStroke keyStroke) {
            if (keyStroke != null && keyStroke.getKeyType() == KeyType.F12) {
                openDevConsole();
                return true;
            }

            return super.handleInput(keyStroke);
        }
    }

    public LanternaMenuEngine(Screen screen, Starship playerShip, Inventory inventory) {
        this.screen = screen;
        this.playerShip = playerShip;
        this.inventory = inventory;
        this.gui = new MultiWindowTextGUI(screen);
    }

    public void updateConsole(String text) {
        if (text == null) {
            text = "";
        }

        consoleLines.clear();
        consoleLines.addAll(Arrays.asList(text.split("\\n", -1)));

        if (consoleLines.size() > 200) {
            consoleLines.subList(0, consoleLines.size() - 200).clear();
        }

        refreshConsoleBox();
    }

    public void startMainMenu() {
        try {
            runMenu(MenuChoice.MAIN);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                screen.stopScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openShipStatusMenu() {
        try {
            runMenu(MenuChoice.SHIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openNavigationMenu() {
        try {
            runMenu(MenuChoice.NAVIGATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMovementMenu() {
        try {
            runMenu(MenuChoice.MOVEMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openInventoryMenu() {
        try {
            runMenu(MenuChoice.INVENTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openinventoryAndCraftingMenu() {
        openInventoryMenu();
    }

    public void openListStars() {
        try {
            runMenu(MenuChoice.LISTSTARS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openListPlanets() {
        try {
            runMenu(MenuChoice.LISTPLANETS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPlanetOptions()
    {
        try {
            runMenu(MenuChoice.PLANETOPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCraftingAndUpgradesMenu() {
        try {
            runMenu(MenuChoice.CRAFTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDevConsole() {
        String command = TextInputDialog.showDialog(
                gui,
                "Dev Console",
                "Commands: add resource <name> <amount>, add upgrade <scan|engine|cargo>, refuel, help",
                "");

        if (command == null) {
            return;
        }

        String result = runDevCommand(command.trim());
        if (!result.isEmpty()) {
            GameOutput.println(result);
        }
    }

    private void runMenu(MenuChoice startingMenu) throws IOException {
        MenuChoice currentMenu = startingMenu;

        while (currentMenu != MenuChoice.EXIT) {
            if (currentMenu == MenuChoice.MAIN) {
                currentMenu = showMainMenu();
            } else if (currentMenu == MenuChoice.NAVIGATION) {
                currentMenu = showNavigationMenu();
            } else if (currentMenu == MenuChoice.MOVEMENT) {
                currentMenu = showMovementMenu();
            } else if (currentMenu == MenuChoice.SHIP) {
                currentMenu = showShipStatusMenu();
            } else if (currentMenu == MenuChoice.INVENTORY) {
                currentMenu = showInventoryMenu();
            } else if (currentMenu == MenuChoice.CRAFTING) {
                currentMenu = showCraftingMenu();
            } else if (currentMenu == MenuChoice.LISTSTARS) {
                currentMenu = listStars();
            } else if (currentMenu == MenuChoice.LISTPLANETS) {
                currentMenu = listPlanets();
            } else if (currentMenu == MenuChoice.PLANETOPTIONS) {
                currentMenu = planetOptions();
            } else {
                currentMenu = MenuChoice.EXIT;
            }
        }
    }

    private MenuChoice showMainMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.EXIT};
        BasicWindow window = new DevWindow("Stellar Terminal");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("STELLAR TERMINAL"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Navigation", () -> {
            GameOutput.println("Navigation system opened.");
            nextChoice[0] = MenuChoice.NAVIGATION;
            window.close();
        }));
        buttons.addComponent(new Button("View " + Main.playerShip.getName() + " Status and Upgrades", () -> {
            GameOutput.println("Ship system opened.");
            nextChoice[0] = MenuChoice.SHIP;
            window.close();
        }));
        buttons.addComponent(new Button("Inventory and Crafting", () -> {
            GameOutput.println("Inventory system opened.");
            nextChoice[0] = MenuChoice.INVENTORY;
            window.close();
        }));
        buttons.addComponent(new Button("Dev Console (F12)", this::openDevConsole));
        buttons.addComponent(new Button("Exit Game", () -> {
            nextChoice[0] = MenuChoice.EXIT;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice showNavigationMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new DevWindow("Navigation");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("NAVIGATION"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Move", () -> {
            GameOutput.println("Movement System opened");
            nextChoice[0] = MenuChoice.MOVEMENT;
            window.close();
        }));
        buttons.addComponent(new Button("Get current position", () -> GameOutput.println("You are in Sector " + Main.getPosition() + ".")));
        buttons.addComponent(new Button("Scan Surrounding Stars", () -> GameOutput.println(Main.surroundings())));
        buttons.addComponent(new Button("Visit Star System (WARNING: VISITING AND LEAVING STAR SYSTEMS/PLANETS COSTS FUEL)", () -> {
            GameOutput.println("Star Systems opened");
            nextChoice[0] = MenuChoice.LISTSTARS;
            window.close();
        }));
        buttons.addComponent(new Button("Back", () -> {
            GameOutput.println("Returned");
            nextChoice[0] = MenuChoice.MAIN;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice listStars() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new BasicWindow("Stars");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("STARS"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));

        ArrayList<Star> sector = new ArrayList<>(Main.getSector());

        buttons.addComponent(new Button(sector.get(0).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(0));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button(sector.get(1).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(1));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button(sector.get(2).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(2));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button(sector.get(3).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(3));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button(sector.get(4).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(4));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button(sector.get(5).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(5));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button(sector.get(6).getName(), () -> {
            Main.playerShip.useFuel(20);
            Main.setChoiceStar(sector.get(6));
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        buttons.addComponent(new Button("Back", () -> {
            GameOutput.println("Returned");
            nextChoice[0] = MenuChoice.NAVIGATION;
            window.close();
        }));

        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice listPlanets() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new BasicWindow("Planets");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("PLANETS"));
        root.addComponent(new Label(""));

        Star choice = Main.getChoiceStar();

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));

        ArrayList<Exoplanet> sector = new ArrayList<>(choice.getSystem());
        buttons.addComponent(new Button(sector.get(0).getName(), () -> {
            Main.setChoicePlanet(sector.get(0));
            Main.playerShip.useFuel(5);
            nextChoice[0] = MenuChoice.PLANETOPTIONS;
            window.close();
        }));

        int numPlanets = choice.getNumPlanets();

        if (numPlanets > 1)
        {
            buttons.addComponent(new Button(sector.get(1).getName(), () -> {
                Main.setChoicePlanet(sector.get(1));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 2)
        {
            buttons.addComponent(new Button(sector.get(2).getName(), () -> {
                Main.setChoicePlanet(sector.get(2));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 3)
        {
            buttons.addComponent(new Button(sector.get(3).getName(), () -> {
                Main.setChoicePlanet(sector.get(3));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 4)
        {
            buttons.addComponent(new Button(sector.get(4).getName(), () -> {
                Main.setChoicePlanet(sector.get(4));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 5)
        {
            buttons.addComponent(new Button(sector.get(5).getName(), () -> {
                Main.setChoicePlanet(sector.get(5));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 6)
        {
            buttons.addComponent(new Button(sector.get(6).getName(), () -> {
                Main.setChoicePlanet(sector.get(6));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 7)
        {
            buttons.addComponent(new Button(sector.get(7).getName(), () -> {
                Main.setChoicePlanet(sector.get(7));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        if (numPlanets > 8)
        {
            buttons.addComponent(new Button(sector.get(8).getName(), () -> {
                Main.setChoicePlanet(sector.get(8));
                Main.playerShip.useFuel(5);
                nextChoice[0] = MenuChoice.PLANETOPTIONS;
                window.close();
            }));
        }

        buttons.addComponent(new Button("Scan planets", () -> {
            for (int i = 0; i < choice.getNumPlanets(); i ++)
            {
                if (Main.playerShip.getScanLevel())
                {
                    GameOutput.println(choice.getSystem().get(i).scan());
                }
                else
                {
                    GameOutput.println(choice.getSystem().get(i).basicScan());
                }
            }
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));

        buttons.addComponent(new Button("Back", () -> {
            GameOutput.println("Returned");
            nextChoice[0] = MenuChoice.LISTSTARS;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);
        return nextChoice[0];
    }

    private MenuChoice planetOptions() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new BasicWindow("Options");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("OPTIONS"));
        root.addComponent(new Label(""));

        Exoplanet choice = Main.getChoicePlanet();

        boolean isGasOrIceGiant = (choice.type().equals("Gas Giant") || choice.type().equals("Ice Giant"));
        ArrayList<String> resources = choice.getResources();

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));

        // "Print landing image"
        // (If NOT Gas/Ice Giant) Mine
        // (If NOT Gas/Ice Giant) Exploration
        // "Repairs"
        // (If Gas/Ice Giant) Collect Gasses
        // (If NOT Gas/Ice Giant) Land - Generates a 2D array based on planet and allows for exploration on this plane
        // and more-(James)
        // "Visit Moons (JARED - If we have time)"

        if (Main.playerShip.getScanLevel())
        {
            buttons.addComponent(new Button("Scan for resources", () -> {
                for (String resource : resources)
                {
                    GameOutput.println(resource);
                }
            }));
        }
        else
        {
            buttons.addComponent(new Button("Scan for resources (Top two)", () -> {
                for (int i = 0; i < resources.size(); i ++)
                {
                    if (i == 2)
                    {
                        break;
                    }
                    GameOutput.println(resources.get(i));
                }
            }));
        }
        
        buttons.addComponent(new Button("Back", () -> {
            GameOutput.println("Returned");
            nextChoice[0] = MenuChoice.LISTPLANETS;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice showMovementMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.NAVIGATION};
        BasicWindow window = new DevWindow("Movement");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("MOVEMENT"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Move Forward (x)", () -> moveAndReturn(0, nextChoice, window)));
        buttons.addComponent(new Button("Move Backward (x)", () -> moveAndReturn(1, nextChoice, window)));
        buttons.addComponent(new Button("Move Up (y)", () -> moveAndReturn(2, nextChoice, window)));
        buttons.addComponent(new Button("Move Down (y)", () -> moveAndReturn(3, nextChoice, window)));
        buttons.addComponent(new Button("Move Right (z)", () -> moveAndReturn(4, nextChoice, window)));
        buttons.addComponent(new Button("Move Left (z)", () -> moveAndReturn(5, nextChoice, window)));
        buttons.addComponent(new Button("Back", () -> {
            GameOutput.println("Returned");
            nextChoice[0] = MenuChoice.NAVIGATION;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice showShipStatusMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new DevWindow(Main.playerShip.getName() + " Status");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label(Main.playerShip.getName().toUpperCase() + "STATUS"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Show " + Main.playerShip.getName() + " Status", () -> GameOutput.println(playerShip.getStatus())));
        buttons.addComponent(new Button("Show Installed Upgrades", () -> GameOutput.println(playerShip.getUpgradeStatus())));
        buttons.addComponent(new Button("Inventory and Crafting", () -> {
            GameOutput.println("Inventory system opened.");
            nextChoice[0] = MenuChoice.INVENTORY;
            window.close();
        }));
        buttons.addComponent(new Button("Back", () -> {
            nextChoice[0] = MenuChoice.MAIN;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice showInventoryMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new DevWindow("Inventory and Crafting");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("INVENTORY AND CRAFTING"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("View Inventory", () -> GameOutput.println(inventory.getInventorySummary())));
        buttons.addComponent(new Button("Open Crafting", () -> {
            GameOutput.println("Crafting system opened.");
            nextChoice[0] = MenuChoice.CRAFTING;
            window.close();
        }));
        buttons.addComponent(new Button("Back", () -> {
            nextChoice[0] = MenuChoice.MAIN;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private MenuChoice showCraftingMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.INVENTORY};
        BasicWindow window = new DevWindow("Crafting");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("CRAFTING"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button(craftingRecipes[0], () -> craftCopperWire()));
        buttons.addComponent(new Button(craftingRecipes[1], () -> craftFuelCell()));
        buttons.addComponent(new Button(craftingRecipes[2], () -> craftIronMesh()));
        buttons.addComponent(new Button(craftingRecipes[3], () -> craftAdvancedFuelCell()));
        buttons.addComponent(new Button(craftingRecipes[4], () -> craftAdvancedInfoGrabber()));
        buttons.addComponent(new Button(craftingRecipes[5], () -> craftScannerUpgrade()));
        buttons.addComponent(new Button(craftingRecipes[6], () -> craftEngineUpgrade()));
        buttons.addComponent(new Button(craftingRecipes[7], () -> craftCargoSpace()));
        buttons.addComponent(new Button("Back", () -> {
            nextChoice[0] = MenuChoice.INVENTORY;
            window.close();
        }));
        root.addComponent(buttons);

        root.addComponent(new Label(""));
        root.addComponent(new Label("Console"));
        consoleBox = createConsoleBox();
        root.addComponent(consoleBox);
        refreshConsoleBox();

        window.setComponent(root);
        gui.addWindowAndWait(window);

        return nextChoice[0];
    }

    private TextBox createConsoleBox() {
        TextBox box = new TextBox(new com.googlecode.lanterna.TerminalSize(80, 12), TextBox.Style.MULTI_LINE);
        box.setReadOnly(true);
        return box;
    }

    private void refreshConsoleBox() {
        if (consoleBox != null) {
            consoleBox.setText(String.join("\n", consoleLines));
            int firstVisibleLine = Math.max(0, consoleLines.size() - consoleBox.getSize().getRows());
            consoleBox.getRenderer().setViewTopLeft(new TerminalPosition(0, firstVisibleLine));
        }
    }

    private void moveAndReturn(int moveType, MenuChoice[] nextChoice, BasicWindow window) {
        if (Main.movement(moveType)) {
            GameOutput.println("Successful!");
        } else {
            GameOutput.println("Failure. You cannot move in that direction.");
        }

        nextChoice[0] = MenuChoice.NAVIGATION;
        window.close();
    }

    private void craftCopperWire() {
        if (inventory.hasItem("Copper Deposit", 2)) {
            inventory.removeItems("Copper Deposit", 2);
            inventory.addItem("Copper Wire", 1);
            GameOutput.println("Crafted Copper Wire!");
        } else {
            GameOutput.println("Not enough Copper Deposit.");
        }
    }

    private void craftFuelCell() {
        if (inventory.hasItem("Hydrogen Gas", 5) && inventory.hasItem("Helium Gas", 1)) {
            inventory.removeItems("Hydrogen Gas", 5);
            inventory.removeItems("Helium Gas", 1);
            inventory.addItem("Fuel Cell", 1);
            GameOutput.println("Crafted Fuel Cell!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }

    private void craftIronMesh() {
        if (inventory.hasItem("Iron ore", 2)) {
            inventory.removeItems("Iron ore", 2);
            inventory.addItem("Iron Mesh", 1);
            GameOutput.println("Crafted Iron Mesh!");
        } else {
            GameOutput.println("Not enough Iron ore.");
        }
    }

    private void craftAdvancedFuelCell() {
        if (inventory.hasItem("Uranium", 1)) {
            inventory.removeItems("Uranium", 1);
            inventory.addItem("Advanced Fuel Cell", 1);
            GameOutput.println("Crafted Advanced Fuel Cell!");
        } else {
            GameOutput.println("Not enough Uranium.");
        }
    }

    private void craftAdvancedInfoGrabber() {
        if (inventory.hasItem("Uranium", 2) && inventory.hasItem("Ancient Artifact", 1)) {
            inventory.removeItems("Uranium", 2);
            inventory.removeItems("Ancient Artifact", 1);
            inventory.addItem("Advanced Info-Grabber", 1);
            GameOutput.println("Crafted Advanced Info-Grabber!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }

    private void craftScannerUpgrade() {
        if (inventory.hasItem("Advanced Info-Grabber", 1) && inventory.hasItem("Copper Wire", 5)) {
            inventory.removeItems("Advanced Info-Grabber", 1);
            inventory.removeItems("Copper Wire", 5);
            playerShip.upgradeScan();
            GameOutput.println("Scanner Upgrade installed!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }

    private void craftEngineUpgrade() {
        if (inventory.hasItem("Rare rocky Elements", 3)
                && inventory.hasItem("Uranium", 2)
                && inventory.hasItem("Iron ore", 8)
                && inventory.hasItem("Copper Deposit", 3)
                && inventory.hasItem("Copper Wire", 3)
                && inventory.hasItem("Advanced Fuel Cell", 1)) {
            inventory.removeItems("Rare rocky Elements", 3);
            inventory.removeItems("Uranium", 2);
            inventory.removeItems("Iron ore", 8);
            inventory.removeItems("Copper Deposit", 3);
            inventory.removeItems("Copper Wire", 3);
            inventory.removeItems("Advanced Fuel Cell", 1);
            playerShip.upgradeEngine();
            GameOutput.println("Engine Upgrade installed!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }

    private void craftCargoSpace() {
        if (inventory.hasItem("Iron Mesh", 2) && inventory.hasItem("Alien Fossils", 3)) {
            inventory.removeItems("Iron Mesh", 2);
            inventory.removeItems("Alien Fossils", 3);
            playerShip.upgradeCargo();
            GameOutput.println("Cargo Space upgraded!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }

    private String runDevCommand(String rawCommand) {
        if (rawCommand.isEmpty()) {
            return "No command entered.";
        }

        String[] parts = rawCommand.split("\\s+");
        String action = parts[0].toLowerCase(Locale.ROOT);

        if (action.equals("help")) {
            return "Commands: add resource <name> <amount>, add upgrade <scan,engine,cargo>, refuel";
        }

        if (action.equals("refuel")) {
            playerShip.refuel();
            return "Ship refueled.";
        }

        if (!action.equals("add") || parts.length < 3) {
            return "Unknown command.";
        }

        String subject = parts[1].toLowerCase(Locale.ROOT);

        if (subject.equals("upgrade")) {
            String upgradeType = parts[2].toLowerCase(Locale.ROOT);

            if (upgradeType.equals("scan")) {
                playerShip.upgradeScan();
                return "Scan upgrade enabled.";
            }
            if (upgradeType.equals("engine")) {
                playerShip.upgradeEngine();
                return "Engine upgraded.";
            }
            if (upgradeType.equals("cargo")) {
                playerShip.upgradeCargo();
                return "Cargo upgraded.";
            }

            return "Unknown upgrade type.";
        }

        if (subject.equals("resource") || subject.equals("item")) {
            if (parts.length < 4) {
                return "Usage: add resource <name> <amount>";
            }

            int amount;
            try {
                amount = Integer.parseInt(parts[parts.length - 1]);
            } catch (NumberFormatException e) {
                return "Last value must be a number.";
            }

            String name = joinWords(parts, 2, parts.length - 1);
            inventory.addItem(name, amount);
            return amount + " x " + name + " added.";
        }

        return "Unknown command.";
    }

    private String joinWords(String[] parts, int start, int end) {
        StringBuilder text = new StringBuilder();

        for (int i = start; i < end; i++) {
            if (i > start) {
                text.append(' ');
            }
            text.append(parts[i]);
        }

        return text.toString();
    }
}