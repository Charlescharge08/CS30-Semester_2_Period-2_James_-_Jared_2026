import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;

public class LanternaMenuEngine {
    private enum MenuChoice {MAIN,SHIP,INVENTORY,CRAFTING,EXIT
    }

    private final ArrayList<String> consoleLines = new ArrayList<>();
    private final Screen screen;
    private final Starship playerShip;
    private final Inventory inventory;
    private final MultiWindowTextGUI gui;
    private TextBox consoleBox;

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

        consoleLines.add(text);

        if (consoleLines.size() > 200) {
            consoleLines.remove(0);
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

    public void openInventoryMenu() {
        try {
            runMenu(MenuChoice.INVENTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runMenu(MenuChoice startingMenu) throws IOException {
        MenuChoice currentMenu = startingMenu;

        while (currentMenu != MenuChoice.EXIT) {
            if (currentMenu == MenuChoice.MAIN) {
                currentMenu = showMainMenu();
            } else if (currentMenu == MenuChoice.SHIP) {
                currentMenu = showShipStatusMenu();
            } else if (currentMenu == MenuChoice.INVENTORY) {
                currentMenu = showInventoryMenu();
            } else if (currentMenu == MenuChoice.CRAFTING) {
                currentMenu = showCraftingMenu();
            } else {
                currentMenu = MenuChoice.EXIT;
            }
        }
    }

    private MenuChoice showMainMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.EXIT};
        BasicWindow window = new BasicWindow("Stellar Terminal");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("STELLAR TERMINAL"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Navigation", () -> GameOutput.println("Navigation system opened.")));
        buttons.addComponent(new Button("View Ship Status and Upgrades", () -> {
            GameOutput.println("Ship system opened.");
            nextChoice[0] = MenuChoice.SHIP;
            window.close();
        }));
        buttons.addComponent(new Button("Inventory and Crafting", () -> {
            GameOutput.println("Inventory system opened.");
            nextChoice[0] = MenuChoice.INVENTORY;
            window.close();
        }));
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

    private MenuChoice showShipStatusMenu() throws IOException {
        final MenuChoice[] nextChoice = {MenuChoice.MAIN};
        BasicWindow window = new BasicWindow("Ship Status");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("SHIP STATUS"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Show Ship Status", () -> GameOutput.println(playerShip.getStatus())));
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
        BasicWindow window = new BasicWindow("Inventory and Crafting");
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
        BasicWindow window = new BasicWindow("Crafting");
        Panel root = new Panel(new LinearLayout(Direction.VERTICAL));

        root.addComponent(new Label("CRAFTING"));
        root.addComponent(new Label(""));

        Panel buttons = new Panel(new LinearLayout(Direction.VERTICAL));
        buttons.addComponent(new Button("Craft Copper Wire", () -> handleCrafting(0, true)));
        buttons.addComponent(new Button("Craft Fuel Cell", () -> handleCrafting(1, true)));
        buttons.addComponent(new Button("Craft Steel Alloy", () -> handleCrafting(2, true)));
        buttons.addComponent(new Button("Craft Electronics Kit", () -> handleCrafting(3, true)));
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
        TextBox box = new TextBox(new TerminalSize(80, 12), TextBox.Style.MULTI_LINE);
        box.setReadOnly(true);
        return box;
    }

    private void refreshConsoleBox() {
        if (consoleBox != null) {
            consoleBox.setText(String.join("\n", consoleLines));
        }
    }

    private boolean handleCrafting(int selectedIndex, boolean inMenu) {
        if (selectedIndex == 0) {
            if (inventory.hasItem("Copper Deposit", 2)) {
                inventory.removeItems("Copper Deposit", 2);
                inventory.addItem("Copper Wire", 1);
                GameOutput.println("Crafted Copper Wire!");
            } else {
                GameOutput.println("Not enough Copper Deposit.");
            }
        } else if (selectedIndex == 1) {
            if (inventory.hasItem("Hydrogen Gas", 5)) {
                inventory.removeItems("Hydrogen Gas", 5);
                inventory.addItem("Fuel Cell", 1);
                GameOutput.println("Crafted Fuel Cell!");
            } else {
                GameOutput.println("Not enough Hydrogen Gas.");
            }
        } else if (selectedIndex == 2) {
            if (inventory.hasItem("Iron ore", 3) && inventory.hasItem("Copper Deposit", 1)) {
                inventory.removeItems("Iron ore", 3);
                inventory.removeItems("Copper Deposit", 1);
                inventory.addItem("Steel Alloy", 1);
                GameOutput.println("Crafted Steel Alloy!");
            } else {
                GameOutput.println("Not enough materials.");
            }
        } else if (selectedIndex == 3) {
            if (inventory.hasItem("Copper Wire", 3) && inventory.hasItem("Rare rocky Elements", 1)) {
                inventory.removeItems("Copper Wire", 3);
                inventory.removeItems("Rare rocky Elements", 1);
                inventory.addItem("Electronics Kit", 1);
                GameOutput.println("Crafted Electronics Kit!");
            } else {
                GameOutput.println("Not enough materials.");
            }
        } else if (selectedIndex == 4) {
            return false;
        }

        return true;
    }
}