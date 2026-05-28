import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Terminal_Oppener {
    
    public Screen openTerminal() {
        try {
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            factory.setInitialTerminalSize(new TerminalSize(160, 50));
            
            Terminal terminal = factory.createTerminalEmulator(); 

            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null); 

            return screen;

        } catch (Exception e) {
            System.out.println("Error opening the terminal window!");
            e.printStackTrace();
        }
        return null;
    }
}