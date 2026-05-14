import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || !args[0].equals("opened")) {

            Terminal_Oppener.main(new String[0]);
            // stops the vscode terminal from opening
            return;
        }
        runApp();
        System.exit(0);

    }

     private static void runApp() {
        //Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("This is the command terminal for the game.");

        System.out.print("Enter a word of your choice: ");
        String input = scanner.nextLine();

        System.out.println("You typed: " + input);
        System.out.println("Press enter to exit...");
        scanner.nextLine();
        //ends
        scanner.close();
        Star proximaCentauri = new Star(134, 1.5, "Proxima Centauri", "Red Dwarf", "Red", 3);
        Exoplanet proximaCentauriB = new Exoplanet(0.0037, 0.11, "Proxima Centauri B", "Superearth", false, 0, 234.0, "Incredibly Minimal", proximaCentauri);
        System.out.println(proximaCentauri.basicScan());
        System.out.println(proximaCentauri.scan());
        System.out.println(proximaCentauriB.basicScan());
        System.out.println(proximaCentauriB.scan());
    }
}
