public class GameOutput {
     private static GameConsole gameConsole = null;
    private static LanternaMenuEngine engine = null;
    
    /**
     * Initialize static output with a console and UI engine.
     * console game console to write to
     * menuEngine UI engine to refresh
     */
    public static void initialize(GameConsole console, LanternaMenuEngine menuEngine) {
        gameConsole = console;
        engine = menuEngine;
    }
    
    /**
     * Print a line to the game console if available
     * text text to print
     */
    public static void println(String text) {
        if (gameConsole != null && engine != null) {
            gameConsole.println(text, engine);
        } else {//fallback
            System.out.println("GameOutput not working! " + text);
        }
    }
    
    /**
     * Print text without an extra line break to the console
     * text to print
     */
    public static void print(String text) {
        if (gameConsole != null && engine != null) {
            gameConsole.println(text, engine);
        } else {
            System.out.print(text);
        }
    }
    
    /**
     * println.
     * text text to print
     */
    public static void printLine(String text) {
        println(text);
    }
}
