public class GameOutput {
     private static GameConsole gameConsole = null;
    private static LanternaMenuEngine engine = null;
    
    public static void initialize(GameConsole console, LanternaMenuEngine menuEngine) {
        gameConsole = console;
        engine = menuEngine;
    }
    
    public static void println(String text) {
        if (gameConsole != null && engine != null) {
            gameConsole.println(text, engine);
        } else {//fallback
            System.out.println("GameOutput not working! " + text);
        }
    }
    
    public static void print(String text) {
        if (gameConsole != null && engine != null) {
            gameConsole.println(text, engine);
        } else {
            System.out.print(text);
        }
    }
    
    public static void printLine(String text) {
        println(text);
    }
}
