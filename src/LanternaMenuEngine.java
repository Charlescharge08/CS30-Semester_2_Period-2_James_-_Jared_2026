import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;

public class LanternaMenuEngine { 
    private ArrayList<String> consoleLines = new ArrayList<>();
    private Screen screen;
    private TextGraphics textGraphics;
    private Starship playerShip;
    private Inventory inventory;
    private int scrollOffset = 0;
    private MultiWindowTextGUI gui;
    private TextBox consoleBox;
    private TextBox inputBox;


    private int lastWidth = -1;
    private int lastHeight = -1;

    public void initGUI() {
    
        gui = new MultiWindowTextGUI(screen);
    
        consoleBox = new TextBox(new TerminalSize(80, 20),
                TextBox.Style.MULTI_LINE);
    
        consoleBox.setReadOnly(true);
    
        inputBox = new TextBox(new TerminalSize(80, 1));
    }


    public LanternaMenuEngine(Screen screen, Starship playerShip, Inventory inventory) {
        this.screen = screen;
        this.playerShip = playerShip;
        this.inventory = inventory;
        this.textGraphics = screen.newTextGraphics();
    }// end of LanternaMenuEngine
    
    public void appendConsole(String text) {

        consoleLines.add(text);

        if (consoleLines.size() > 200) {
            consoleLines.remove(0);
        }
    }

    public void updateConsole(String text) {
        appendConsole(text);
    }

    public void startMainMenu() {

        try {
            String[] options = {"Navigation", "View Ship Status and Upgrades", "Inventory and Crafting", "Exit Game"};
            int selectedIndex = 0;
            boolean keepRunning = true;
 
            while (keepRunning) {
 
                int width = screen.getTerminalSize().getColumns();
                int height = screen.getTerminalSize().getRows();
 

                if(width != lastWidth || height != lastHeight){
                    lastWidth = width;      
                    lastHeight = height;    
                    screen.clear();
                    System.out.println("Screen resized to: " + width + "x" + height);
                }

                drawAll(options, selectedIndex);
 
                KeyStroke keyStroke = screen.pollInput();
                
                if(keyStroke != null) {  
                    KeyType keyType = keyStroke.getKeyType();
 
                    if(keyType == KeyType.ArrowUp) {
                        selectedIndex--;
                        if (selectedIndex < 0) {
                            selectedIndex = options.length - 1;
                        }
                    }else if(keyType == KeyType.ArrowDown) {
                        selectedIndex++;
                        if (selectedIndex >= options.length) {
                            selectedIndex = 0;
                        }
                    }else if(keyType == KeyType.Enter) {
                        keepRunning = handleSelection(selectedIndex);
                    }else if(keyType == KeyType.Character) {
                        char c = keyStroke.getCharacter();
                        if (Character.isDigit(c)) {
                            int numPressed = Character.getNumericValue(c);
                            if (numPressed >= 1 && numPressed <= options.length) {
                                selectedIndex = numPressed - 1; 
                                keepRunning = handleSelection(selectedIndex);
                            }
                        }
                    }
                }
                
                // the way i have it set up is that it redraws every cpu clock tik. this stops cpu from being pined at 100 and stop the screen from flickering. 
                Thread.sleep(20);
            }
 
            screen.stopScreen();
            //prints error
        }catch(IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {//prints why the cpu sleep did not work
            e.printStackTrace();
        }
    }// end of startMainMenu()


    public void openShipStatusMenu() {

        try {
            String[] options = {"Show Ship Status", "Show Installed Upgrades", "Inventory and Crafting", "Back"};
            int selectedIndex = 0;
            boolean keepRunning = true;
 
            while (keepRunning) {
 
                int width = screen.getTerminalSize().getColumns();
                int height = screen.getTerminalSize().getRows();
 

                if(width != lastWidth || height != lastHeight){
                    lastWidth = width;      
                    lastHeight = height;    
                    screen.clear();
                    System.out.println("Screen resized to: " + width + "x" + height);
                }

                drawAll(options, selectedIndex);
 
                KeyStroke keyStroke = screen.pollInput();
                
                if(keyStroke != null) {  
                    KeyType keyType = keyStroke.getKeyType();
 
                    if(keyType == KeyType.ArrowUp) {
                        selectedIndex--;
                        if (selectedIndex < 0) {
                            selectedIndex = options.length - 1;
                        }
                    }else if(keyType == KeyType.ArrowDown) {
                        selectedIndex++;
                        if (selectedIndex >= options.length) {
                            selectedIndex = 0;
                        }
                    }else if(keyType == KeyType.Enter) {
                        keepRunning = handleShipMenuSelection(selectedIndex);
                    }else if(keyType == KeyType.Character) {
                        char c = keyStroke.getCharacter();
                        if (Character.isDigit(c)) {
                            int numPressed = Character.getNumericValue(c);
                            if (numPressed >= 1 && numPressed <= options.length) {
                                selectedIndex = numPressed - 1; 
                                keepRunning = handleShipMenuSelection(selectedIndex);
                            }
                        }
                    }
                }
                
                Thread.sleep(20);
            }
 
            screen.stopScreen();
 
        }catch(IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openInventoryMenu() {

        try {
            String[] options = {"View Inventory", "Open Crafting", "Back"};
            int selectedIndex = 0;
            boolean keepRunning = true;
 
            while (keepRunning) {
 
                int width = screen.getTerminalSize().getColumns();
                int height = screen.getTerminalSize().getRows();
 

                if(width != lastWidth || height != lastHeight){
                    lastWidth = width;      
                    lastHeight = height;    
                    screen.clear();
                    System.out.println("Screen resized to: " + width + "x" + height);
                }

                drawAll(options, selectedIndex);
 
                KeyStroke keyStroke = screen.pollInput();
                
                if(keyStroke != null) {  
                    KeyType keyType = keyStroke.getKeyType();
 
                    if(keyType == KeyType.ArrowUp) {
                        selectedIndex--;
                        if (selectedIndex < 0) {
                            selectedIndex = options.length - 1;
                        }
                    }else if(keyType == KeyType.ArrowDown) {
                        selectedIndex++;
                        if (selectedIndex >= options.length) {
                            selectedIndex = 0;
                        }
                    }else if(keyType == KeyType.Enter) {
                        keepRunning = handleInventoryMenuSelection(selectedIndex);
                    }else if(keyType == KeyType.Character) {
                        char c = keyStroke.getCharacter();
                        if (Character.isDigit(c)) {
                            int numPressed = Character.getNumericValue(c);
                            if (numPressed >= 1 && numPressed <= options.length) {
                                selectedIndex = numPressed - 1; 
                                keepRunning = handleInventoryMenuSelection(selectedIndex);
                            }
                        }
                    }
                }
                
                // the way i have it set up is that it redraws every cpu clock tik. this stops cpu from being pined at 100 and stop the screen from flickering. 
                Thread.sleep(20);
            }
 
            screen.stopScreen();
 
        }catch(IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }// end of openInventoryMenu

    private void openCraftingMenu(){

        try {
            String[] options = {"View Inventory", "Open Crafting", "Back"};
            int selectedIndex = 0;
            boolean keepRunning = true;
 
            while (keepRunning) {
 
                int width = screen.getTerminalSize().getColumns();
                int height = screen.getTerminalSize().getRows();
 

                if(width != lastWidth || height != lastHeight){
                    lastWidth = width;      
                    lastHeight = height;    
                    screen.clear();
                    System.out.println("Screen resized to: " + width + "x" + height);
                }

                drawAll(options, selectedIndex);
 
                KeyStroke keyStroke = screen.pollInput();
                
                if(keyStroke != null) {  
                    KeyType keyType = keyStroke.getKeyType();
 
                    if(keyType == KeyType.ArrowUp) {
                        selectedIndex--;
                        if (selectedIndex < 0) {
                            selectedIndex = options.length - 1;
                        }
                    }else if(keyType == KeyType.ArrowDown) {
                        selectedIndex++;
                        if (selectedIndex >= options.length) {
                            selectedIndex = 0;
                        }
                    }else if(keyType == KeyType.Enter) {
                        keepRunning = handleInventoryMenuSelection(selectedIndex);
                    }else if(keyType == KeyType.Character) {
                        char c = keyStroke.getCharacter();
                        if (Character.isDigit(c)) {
                            int numPressed = Character.getNumericValue(c);
                            if (numPressed >= 1 && numPressed <= options.length) {
                                selectedIndex = numPressed - 1; 
                                keepRunning = handleInventoryMenuSelection(selectedIndex);
                            }
                        }
                    }
                }
                
                // the way i have it set up is that it redraws every cpu clock tik. this stops cpu from being pined at 100 and stop the screen from flickering. 
                Thread.sleep(20);
            }
 
            screen.stopScreen();
 
        }catch(IOException e) {
            e.printStackTrace();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }// end of openCraftingMenu


    private boolean handleCrafting(int selectedIndex, boolean inMenu) {

    if (selectedIndex == 0) {

        if (inventory.hasItem("Copper Deposit", 2)) {

            inventory.removeItems("Copper Deposit", 2);
            inventory.addItem("Copper Wire", 1);

            GameOutput.println("Crafted Copper Wire!");
        } else {
            GameOutput.println("Not enough Copper Deposit.");
        }
    }else if (selectedIndex == 1) {

        if (inventory.hasItem("Hydrogen Gas", 5)) {

            inventory.removeItems("Hydrogen Gas", 5);
            inventory.addItem("Fuel Cell", 1);

            GameOutput.println("Crafted Fuel Cell!");
        } else {
            GameOutput.println("Not enough Hydrogen Gas.");
        }
    }else if (selectedIndex == 2) {

        if (inventory.hasItem("Iron ore", 3) &&
            inventory.hasItem("Copper Deposit", 1)) {

            inventory.removeItems("Iron ore", 3);
            inventory.removeItems("Copper Deposit", 1);
            inventory.addItem("Steel Alloy", 1);

            GameOutput.println("Crafted Steel Alloy!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }else if (selectedIndex == 3) {

        if (inventory.hasItem("Copper Wire", 3) &&
            inventory.hasItem("Rare rocky Elements", 1)) {

            inventory.removeItems("Copper Wire", 3);
            inventory.removeItems("Rare rocky Elements", 1);
            inventory.addItem("Electronics Kit", 1);

            GameOutput.println("Crafted Electronics Kit!");
        } else {
            GameOutput.println("Not enough materials.");
        }
    }else if (selectedIndex == 4) {
        return false;
    }

    return true;
}


    private void drawAll(String[] options, int selectedIndex) throws IOException {
        screen.clear();

    drawMenu(options, selectedIndex);
    drawSeparator();
    drawConsole();

    screen.refresh();

    }


    //Draws the menu
    private void drawMenu(String[] options, int selectedIndex) throws IOException {
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();
 
        int separator = getSeparator(height);
 
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        String header = "STELLAR TERMINAL";
        String paddedHeader = header + " ".repeat(Math.max(0, width - header.length()));
        textGraphics.putString(0, 1, paddedHeader);
        
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
 
        if(options != null){
            int maxMenuHeight = separator - 3;
            int spacing = Math.max(1, maxMenuHeight / options.length);
            
            for (int i = 0; i < options.length; i++) {
                int row = 3 + (i * spacing);
                if (row >= separator){
                    break;
                } 
 
                if (i == selectedIndex) {
                    textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
                    textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
                    String text = "[ " + (i + 1) + " ] " + options[i];
                    textGraphics.putString(4, row, text);
                } else {
                    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                    textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                    String text = "  " + (i + 1) + "   " + options[i];
                    textGraphics.putString(4, row, text);
                }
            }
        }
    }


    //draw the separator between the terminal and user
    private void drawSeparator(){
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();
        int y = getSeparator(height);
 
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        
        for (int x = 0; x < width; x++) {
            textGraphics.putString(x, y, "─");
        }
    }


    // separator bar height
    private int getSeparator(int hight){
        return (int) (hight * 0.70);
    }// end of getSeparator

    //handleSelection for main menu
    private boolean handleSelection(int selectedIndex) {
        if(selectedIndex ==  0){
            GameOutput.println("Navigation system opened.");
        }else if(selectedIndex == 1){
            GameOutput.println("Ship system opened.");
            openShipStatusMenu();
        }else if(selectedIndex == 2) {
            GameOutput.println("Inventory system opened.");
            openInventoryMenu();
        }else if (selectedIndex == 3) {
            return false;
        }
        return true; 
    }//end of handleSelection

    private boolean handleShipMenuSelection(int selectedIndex) {

        if (selectedIndex == 0) {
            GameOutput.println("Navigation system opened.");
        } 
        else if (selectedIndex == 1) {
            GameOutput.println("Ship system opened.");
            openShipStatusMenu();
        } 
        else if (selectedIndex == 2) {
            GameOutput.println("Inventory system opened.");
        } 
        else if (selectedIndex == 3) {
            startMainMenu();
        }

        return true;
    }//end of handleShipMenuSelection

    private boolean handleInventoryMenuSelection(int selectedIndex) {

        if (selectedIndex == 0) {
            GameOutput.println("Navigation system opened.");
        } 
        else if (selectedIndex == 1) {
            GameOutput.println("Ship system opened.");
            openCraftingMenu();
        } 
        else if (selectedIndex == 2) {
            GameOutput.println("Inventory system opened.");
        } 
        else if (selectedIndex == 3) {
            startMainMenu();
        }

        return true;
    }//end of handleShipMenuSelection

    private boolean handleCraftingMenuSelection(int selectedIndex) {

        if (selectedIndex == 0) {
            GameOutput.println("Navigation system opened.");
        } 
        else if (selectedIndex == 1) {
            GameOutput.println("Ship system opened.");
            openCraftingMenu();
        } 
        else if (selectedIndex == 2) {
            GameOutput.println("Inventory system opened.");
        } 
        else if (selectedIndex == 3) {
            startMainMenu();
        }

        return true;
    }//end of handleShipMenuSelection

    private void drawConsole() {

    int width = screen.getTerminalSize().getColumns();
    int height = screen.getTerminalSize().getRows();

    int separator = getSeparator(height);
    int startY = separator + 1;

    int availableLines = height - startY;

    if (availableLines <= 0) return;

    textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);

    int maxStart = Math.max(0, consoleLines.size() - availableLines);
    int startIndex = Math.max(0, maxStart - scrollOffset);

    for (int i = 0; i < availableLines && (startIndex + i) < consoleLines.size(); i++) {

        String line = consoleLines.get(startIndex + i);

        if (line.length() > width - 2) {
            line = line.substring(0, width - 2);
        }

        textGraphics.putString(1, startY + i, line);
    }
}

}// end of class 