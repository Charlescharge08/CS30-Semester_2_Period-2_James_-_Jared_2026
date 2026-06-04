import java.util.ArrayList;

public class GameConsole {
    private ArrayList<String> lines = new ArrayList<>();

     public void println(String text, LanternaMenuEngine engine) {

        lines.add(text);

        if (lines.size() > 20) {
            lines.remove(0);
        }

        String output = "";

        for (int i = 0; i < lines.size(); i++) {
            output = output + lines.get(i) + "\n";
        }

        engine.updateConsole(output);
    }



}

