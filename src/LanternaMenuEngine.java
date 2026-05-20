import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class LanternaMenuEngine { 
    private Screen screen;
    private TextGraphics textGraphics;

    public LanternaMenuEngine(Screen screen) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
    }

    public void startMainMenu() {
        try {
            String[] options = {
                "Scan Local System",
                "View Ship Status",
                "Open Galaxy Map",
                "Exit Console"
            };
            int selectedIndex = 0;
            boolean keepRunning = true;

            while (keepRunning) {
                drawMenu("STELLAR TERMINAL", options, selectedIndex);

                KeyStroke keyStroke = screen.readInput();
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

            screen.stopScreen();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void drawMenu(String title, String[] options, int selectedIndex) throws IOException {
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.fill(' '); 


        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(1, 1, title );


        for (int i = 0; i < options.length; i++) {
            int row = 4 + (i * 2); 

            if (i == selectedIndex) {
                textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
                textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
                textGraphics.putString(4, row, "[ " + (i + 1) + " ] " + options[i] + " ");
            } else {
                textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                textGraphics.putString(4, row, "  " + (i + 1) + "   " + options[i] + " ");
            }
        }

        screen.refresh();
    }

    private boolean handleSelection(int selectedIndex) {
        if (selectedIndex == 3) {
            return false;
        }
        return true; 
    }
}