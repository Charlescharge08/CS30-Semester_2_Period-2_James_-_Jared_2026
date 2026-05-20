import java.io.*;
public class Terminal_Oppener {
    public static void main(String[] args) {
        // this will pervent the program from oppening multiple terminals
        if (args.length > 0 && args[0].equals("opened")) {
            
            return;
        }
        // Detects the operating system
        String systemType = System.getProperty("os.name").toLowerCase();
        try{
            //java sdk location
            String javaSDK = System.getProperty("java.home") + "/bin/java";
            //Curent program Location
            String classPath = System.getProperty("java.class.path");
            //comand to lanuch the terminal

            // Check if the operating system is Windows
            if (systemType.contains("win")) {
                 new ProcessBuilder( "wt", "cmd", "/c", javaSDK, "-cp", classPath, "Main", "opened").start();
            } else if (systemType.contains("mac")) {
                String command = javaSDK + " -cp " + classPath + " Main opened";
                new ProcessBuilder("osascript", "-e", "tell application \"Terminal\" to do script \"" + command.replace("\"", "\\\"") + "; exit\"").start();
            }else {
                String command = javaSDK + " -cp " + classPath + " Main opened";
                new ProcessBuilder("sh", "-c", "gnome-terminal -- " + command + " || xterm -e " + command ).start();
            }   
        } catch (IOException error) {
            System.out.println("Could not open terminal.");
            error.printStackTrace();
        }
            
    }    


}