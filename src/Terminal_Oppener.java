import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Terminal_Oppener {
    
    public void openTerminal() {
        try {
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            Terminal terminal = factory.createTerminalEmulator(); 

            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null); 

            // Pointing directly to your updated class name
            LanternaMenuEngine ui = new LanternaMenuEngine(screen);
            ui.startMainMenu();

        } catch (Exception e) {
            System.out.println("Error opening the terminal window!");
            e.printStackTrace();
        }
    }
}