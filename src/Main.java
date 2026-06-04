import com.googlecode.lanterna.screen.Screen;   
import java.util.*;
public class Main {
    public static int[][][] map = new int[4][4][4];

    public static int playerX;
    public static int playerY;
    public static int playerZ;

    public static Starship playerShip = new Starship("Excellence");
    public static Inventory inventory = new Inventory();
    
    public static Terminal_Oppener opener = new Terminal_Oppener();

    public static ArrayList<ArrayList<Star>> universe = new ArrayList<>();

    public static Screen screen = opener.openTerminal();
    
    public static GameConsole gameConsole = new GameConsole();

    public static LanternaMenuEngine menu = new LanternaMenuEngine(screen, playerShip, inventory);
    public static void main(String[] args) throws Exception {
        GameOutput.initialize(gameConsole, menu);
        generateUniverse();
        GameOutput.println("You are the final member of humanity after the explosion of the sun. \nYou have lived your whole life in space with your parents, \nlearning how to live off of the various planets and stars around the galaxy. \nNow, they have died, and left you alone with only their ship, the Excellence. \nYou must survive by yourself in the cold galaxy. Your goal is to find \na nice habitable planet somewhere in the galaxy, and settle down.\r\n");
        GameOutput.println("Welcome to the Excellence. This is your lifeline. Choose wisely.");
        /* GameOutput.println("........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        ".................................................................................................::-====++++******#*****#*#************+++*+++++*++=-:..................................................\r\n" + //
        "....................................................................................=+**+=------====-=========--::-=#%%%%%%%%%#%######%%%############**++++***=-........................................\r\n" + //
        "..........................................................................:=+++++*++==================================-....:..:----==++*###%%@@@%%%%%%%########*+++*#+=:................................\r\n" + //
        "..................................................................:=+++++**####+=-==================++++++++++++++++======-:=%@@@@@@@@@@%%#+==::.:-==+#%@@%%%%%%######*++*#+=...........................\r\n" + //
        "............................................................-=+*++**####%%%%#======++++++++=++++++++++++++++++++++++++++++++=-.-#@@@@@@@@@@@@@@@@@@@@%#=:::-=+#%%%%%%%######++*#+-......................\r\n" + //
        "......................................................-+*+++*######%%%#+=--=+=+++++++++++++++++++++++++++**+++++++++++++++*+++==-.+#%@@@@@@@@@@@@@@@@@@@@@@@@%+-::-=#%%%%%%#####*+*#+-..................\r\n" + //
        "................................................-=+++++*#####%##*=---+##==++++++++++++***************************++++********++++=-.=#%%%%%@@@@@@@@@@@@@@@@@@@@@@@@#+---=#%@%%%#####++#*=...............\r\n" + //
        "...........................................:-++++*#####%%##=---+%@@@@%=+++++++++********************************+*****#######*+++++=-.=#######%%%%%@@@@@@@@@@@@@@@@@@@@%+---+#%%%%#####*+**=............\r\n" + //
        ".......................................-=+++**###%%%#+=--+%@@@@@@@@%++******************************#**************##########**+**++==:..--=++*######%%%%@@@@@@@@@@@@@@@@@@#=::=#%%%%%####++#+-.........\r\n" + //
        "...................................-++++**###%%%*=--+%@@@@@@@@@@@@*=++*#####************************************############*****#*+++==...:::::--=++#####%%%@@@@@@@@@@@@@@@@@%=:-=#@@######++#*-.......\r\n" + //
        "...............................:=+*+*####%%#+=--*%@@@@@@@@@@@@@%%++++**############******************############################***+++==:...:::::::---=+#####%%%@@@@@@@@@@@@@@@@+--=#@%%#%###++#+......\r\n" + //
        "............................-+**+*###%%%*=-=#@@@@@@@@@@@@@%%%%#*=+****##############################################################**+++=-......:::::::-:-=+#####%%@@@@@@@@@@@@@@@*--=#@%%#####+*#-....\r\n" + //
        ".........................=+++***##%%#=--+%@@@@@@@@@@@@%%%####++=+*##################################################################**++++=-..........::::::--=+####%%@@@@@@@@@@@@@@%=:-+%@%%####+*#=...\r\n" + //
        ".....................:=+++**###%%#=-=#@@@@@@@@@@@@@%%####++-:-=+**#############################################**####################***+++=-............::-:::--+####%%@@@@@@@@@@@@@@+:-=%@%%##*#++#-..\r\n" + //
        "...................-++++**##%%#=:=#@@@@@@@@@@@@%%%###*+--:..-=+***####################################################################**+++==:.............::-:.:--+####%%@@@@@@@@@@@@@*:-+%@%%####+**..\r\n" + //
        ".................=*++**##%%#+--*@@@@@@@@@@@@%%%###*=-:::::.:=++*########################################################################*+++=-..............:::::.:-+####%%@@@@@@@@@@@@@=:-+@%%####++#=.\r\n" + //
        "..............-++++**##%%*--=%@@@@@@@@@@@@%####*=-:::::....=++*#########################################################################**++==-................:-::--+#####%@@@@@@@@@@@@#:-=%@%##*#*+#+.\r\n" + //
        "............:+++***##%%*-:+@@@@@@@@@@@@%%####+-::--:.::...:=++*########################################################################**+++++=...............:.:-:.--*%##%%%@@@@@@@@@@@@::=#@%%#*#*+#+.\r\n" + //
        "...........+*+**###%%*-.+@@@@@@@@@@@@%#####=-::-:::.......-+**#########################################################################**+++++=-..............::.::.:-+%###%%@@@@@@@@@@@@-:=#@%%#*#*+#+.\r\n" + //
        ".........=*+***##%%#=:=%@@@@@@@@@@@%%####=---:::..........=+**#######################################################################***++***+=-...............::-:.:-+%###%@@@@@@@@@@@@#:-+%%%##**++#-.\r\n" + //
        ".......:#*+***##%@+--#@@@@@@@@@@@%%####=-::::::..........-=+*#####################################################################*******###*+=-................:-:.--##*%#%@@@@@@@@@@@@=:=#@%##***+#+..\r\n" + //
        "......-#++*+##%@%=--%@@@@@@@@@@@%%####--::-:::...........-+++*#################################################################*#******####*++=-..............:::-::-+####%@@@@@@@@@@@@+:-+%%%#*#*+**:..\r\n" + //
        ".....=#++#*##%@#=:-%@@@@@@@@@@@%%###*=-::::::............-+++***#########################################################*#********###%####*++==.............:::-..-=####%@@@@@@@@@@@@#:-+%%##***++*-...\r\n" + //
        "....-#++***#%@%=:-%@@@@@@@@@@@%#####=::--::..............-+*******################################################**************###%%#####**++==............:.-:.:-=##*#%@@@@@@@@@@@@*:-=@%##***=**:....\r\n" + //
        "...-*++#**##%%+-:#@@@@@@@@@@@@%####=-:.:-.:..............-+*###*******#######################################*****#*****######%%%%%######**+++=-..........::::::--+####%@@@@@@@@@@@@=:-*@%##**++#+......\r\n" + //
        "...=#+***##%@#=:-@@@@@@@@@@@@%%####=-:-:::...............-=+*######********#*###*#####**#**#*######******************###%%%%%%%%%%######***++==-.........::::.--=####%%@@@@@@@@@@@#-:+%%##**+++#-.......\r\n" + //
        "..:#*+*####%@+-:+@@@@@@@@@@@@%##*##--.:-::................=+**#########*********************************#****######%%%%%%%%%%%#########***+++==:.......:::::--=####%%@@%@@@@@@@@%=:=#%%##**++*+.........\r\n" + //
        "..:#+*#*###%@+-:+@@@@@@@@@@@@@%####=-::-:::...............-++*######%%%%%%%#########*###################%%%%%%%@@@%%%%%%%%%%############*++++=-.....::::-:--+####%%@@@@@@@@@@@%=:=#@%##**++*+...........\r\n" + //
        "..:#****##%%@+=:=@@@@@@@@@@@@@%####*--::::.:..............:=++**####%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@%%%%%%%%%%%%#%##############***+++-:...:::-:.-=*####%@@@@@@@@@@@@#-:=#%%##*+++++:............\r\n" + //
        "...*#+**##%%@%=::#@@@@@@@@@@@@%%####+--:::::::.............-+++*#########%%%%%%%%%%%%%%%%%%%%%%%%@@%%%%%%%%%%%%%%%%%####################*++==-..:::::-=*###%%%@@@@@@@@@@@%+--+%%%#**+++*=...............\r\n" + //
        "...=#+*#####%@+-.=@@@@@@@@@@@@@@%%###*=-:::::::.............=++**#############%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##%#%##%#############**++==--.::.--=*####%@@@@@@@@@@@@@*--=#%%##***+++-.................\r\n" + //
        "...:+#+*#*#%%@@+=:=%@@@@@@@@@@@@@%%%###*=--::::::...........:=++**############%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%####################*++======-::-=+####%%@@@@@@@@@@@@@#--=#%%##***+++=....................\r\n" + //
        "....:*#+*####%%@*---#@@@@@@@@@@@@@%%%####*=--::-::::.........-=+***##############%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##########**+++++=====++==+####%%%@@@@@@@@@@@@%*--=#%%##***+++=.......................\r\n" + //
        "......+#++####%%@%+-:=%@@@@@@@@@@@@@@%%%####+=-::::::::::.....-=++*###################%##%%%%%%%%%%%%%%%%%%%%#########***++++++===++++*#####%%%@@@@@@@@@@@@@%#=:-+#%%##**++++-..........................\r\n" + //
        ".......=#*+####%%%@#=-:+@@@@@@@@@@@@@@@@%%%####*+=--::::::::...:===+++***####################################*****++++++++++++++*#######%%%@@@@@@@@@@@@@@#=-=+#%%###*++++=-.............................\r\n" + //
        ".........+#++####%%%@%+-:-*@@@@@@@@@@@@@@@@@%%%%####*+=--:::::...-=====++++++++*********************++++++++++++++++++++****######%%%%@@@@@@@@@@@@@@#+--=*#%%###*++++=-.................................\r\n" + //
        "..........:+#*+*####%%%%#=--=#@@@@@@@@@@@@@@@@@@%%%%#####**+==--:.-=+++++++===+++++++++++++++++++++++++++++++++****########%%%%%%@@@@@@@@@@@@@@#+=--+##%%##**++++=-.....................................\r\n" + //
        ".............-**+*#####%%@%#+---+%@@@@@@@@@@@@@@@@@@@@@%%%########***#*****++++++++++++++++++++******#############%%%%%%%%%%@@@@@@@@@@@@@%*==-=+##%%####*++++--.........................................\r\n" + //
        "................=*#+*#####%%%%%#+---=#@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%##############################%%%%%%%%%@%%%%@@@@@@@@@@@@@@@#=---=+##%%####**++++=:..............................................\r\n" + //
        "...................-+#*+*#####%%%%%%*+---=+#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@%*=---=+*###%###***++++=-:...................................................\r\n" + //
        ".......................-+#*+**######%%%%%##+----=+#%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#*+==++*##%##%######*++++++-:.........................................................\r\n" + //
        "...........................:=+##++**######%%%%%%%#*+=--:::-=+#%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%##*++=++*###%%%%%%%#####****++++++=:................................................................\r\n" + //
        ".................................-=+***++*#######%%%%%%%%%%%%###++===----:::::..:--=+++++****####%%%%%%%%%%##%%#######*+++++++=-........................................................................\r\n" + //
        "........................................-=+##**+++**#########%%#%%%%%##%%%%%%%%%%%%%%%%%%%%%%%%##########****+++++++==-:................................................................................\r\n" + //
        "..................................................:-=+**********+******#######*####*******++++++++++++++=--.............................................................................................\r\n" + //
        ".....................................................................::------------::...................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................\r\n" + //
        "........................................................................................................................................................................................................");
        Gas Giant Ascii Art
        GameOutput.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XXxxx++;;;;++xxxXX$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$x+;;;;;;;;;;;;;;;;;;:;;;;++;;;;x$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$x++;;;;;+;+++xxxxxxxxxx+++++;;;;;+x+;::;;+$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&X+;;;;+++;;;+xx+xxxxxxx+++xxxx+++++++++;+++;;;;;;x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$x;;++++xxxxxxxxXxxXxxx+xx++++xx++;++++x+;;;++x+;;;;;;;;+$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&X+;+xxXXxxxxx++xXXXXXXXxxx+;;++++x++;+;;++x+;++;xXxxx++;;;;++X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&x++++xxxxXXxxxxx+XXXxXxXXxx+x+++++x+++++++++++x+++xxxXX+;;;+++;+++&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&$+++++xxXXXXxxxXXXXXxXXXXXXXXXxxxx+xx++xxxx++xxxxxxxx+xxxxxx++;;+++xxx+$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&+++xxxxxxxxxxxXXXxxxXXxxXXXXXXxXXXxx+xxxxxXXXxxxxx+xxxxXx+xxxXxxxx+x++++++$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&x;+x+xxXXXxxxxxxxxxXXxxxxxxXxXXXXXXxxXx+xXXXxxxxxxxxxxxxxXXxxx+xxx++xxx++xxx+x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&X;+xxx+xx+xxx+xXxxxxxxxxxxxxxx+xxXxxxxXxx++x+++;+xxXXxXx++Xxx+x++xx+++++++;+xxxx;X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&+;xxxxxxxxx+++++xxxxxxxxxxxx++x+xxxxxXxxxx+xx;+++++xxXXXXxXXXxx+;++;++xx;;+xxx+xxx+x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&$+xxxxx+xxxxxxx+x+xxxxx+++xxxxxxxx+++xXXXxXxx++xxxxxxxXxXXx+xxxXXxxx++x+xx++;+xXXxXxx+x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&XxxxxXXxxxXXxxxxx+xxxx+xxxx+xxxx+xxxXXxXxxxxxxxxxx+xx+++++xxxxxxXXxxxxxxxxxx+;+xXXXxxXxxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&$+++xxxxxxxxxxxxxxxxxxxxx+++;+x+xxXxxxxx+x+xxxxxxxxxxx+;:;++++xxxxXXXxxxxxXXXxx+xXXX++xxxxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&X++x++xxxxxxxXxxxxXxxxxxxxx+++;;+xxxxxxxxxxxxxxxxxxxxxx+x++++;++xxxxXx+xxXXXXXxxxxXXxxx+xxx+xX&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&$++xx+;;+;++xxxxxxXXxxxxxx++++;;++xxXxXXXXXxxxxxxxxxxxxxx+xx+++++xXXx+x++xX+xxxXxxxXXxxX+++++xX$&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&$++xx+++++++xxxxxxXXxxxxxx++++++++xxxxxxxxxXxxxxx+++xxxxxx++++x+xx+xxxxxx+xxxxxxx;xxXXxXxx+xxx++X&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&xxxx++xx+++xxxxxxxxxxxxxxx+++x+++xxXXx+++xXXXXXx+x++xxxxxxx++x++xxxx++xxxx+xxxx+++xxxXXx++Xxxxx+xX&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&X+++x;+xx++;++xxxxxXxxx++x+xxxx++xxXXXxxXXXXXXXXxxxxxxxxxxxxxxxxxx+xxxXxxxxxxxx+++xxx+xxx++xxxxx+xX$&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&++++++x+++;++;+xxxxxxx+xxxxxxx++xxxXXXXXXx++xXXxxxxxxxxxxxxxxxx+xxxxx+x+xxxxXx+++;+xxxxxx++x+xXx++xx&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&x++x+++x+++;+++++xxxxxxXxxxxxxxxXxxXxxx+xx+;+xxxxxxxxxxxxxx+xxxxxxxx+xxx+xxx+xxxx+;;xxxxxxxxxx+++x++xX&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&x;+++++++++++++xx++xxxx+xxxxxxxxxxxxxxXxxxxxxxxxxxxxxxx+x+++;xxxxxxx++xxxxxxXXxxx+++xxxxxxxx+x+:;x+++x&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&$+++++++++++++xxx+++xxxxxXXxxxxXx++xXxXXXXxXxxxxxxxxxxxx+++xxxx+xx+x+xx++xxxxXXXxx+xxxXx+x++x+xx+;+++;+$&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&x;+x++++x+x+++++xxx++x+xxXx+++xXXXXXXxxXXXxxXXxxxx+;;++x+++x++xxxxx+xxxx+xxxXXxxxXxxXxXx+xxxxxxxx++x+;;x&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&+++x++++++;++++x+++++++xxxxxxxXxxXxxx+xxXxxXXXxxxx+++xx+x++xxxxx+++++xxxxxxXxxxxxxxxxXXxxxxxXxxxx++++;;+&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&++x+++++++;;+++xx++++xxx++x+xxxXxXXXxxx+XXxxxx++xxxxx++xx+;+xxxx++xxx++xxxxXXxxXxxxxxxxxxxxxxxx+xxxx+++x&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&$+++++++;;;+++++++++x+xxx++++++xxXXXXxxXXXxxxxxxxxXxxx+;;++xxxx++++xxXxxx++xxxxxxxxxxXXXxxxxxXxx+xxxx+++x$&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&$+++;+++;:++++x+++xxxxx+xx+;++++xxxxx++xxxXXXXXXXXXXXXx+;;+xXXx+++xx+x++x+xx+xxxx++;+xxxxXxxxXxx++xx++xxx$&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&+++:;;++;++++xx++++x++++++xxxxxxXxx+xxxxxXXXxx+xX$$XXx+;+xxXxx++xXXX++xx+xxxxx+++;;+xxxxXxxxxxx+xxx++xxx$&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&x++;;+++++++++x+++xxx++xxxxxxxxxXxxxxxxxXxxXXxxx+xxXxXxxxxxxxx+++xxxxxxxxxx++xx++;;+xxxxxxxxxxx++++++xxx&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&x++;;;:;+++++xx+++xxxx+xxXXxxx+xxXXxxxxxXXXXxXxxxXxx+xxx;x++++;xxxxxxXxxxXxxx+++;;++xx+xx+xxxxx++++;++xx&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&Xx+;;;;;+++++x++;+++++xxxxxxxxxxxxxxxxxx+xXXXXXxxx+;+xx+;;:;;:++xxxx+xXXXxxxxx++++x+xxxxxxxxxxxXX+::;+xX&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&$x+;;:;;+++++x+;;++x+xxxxxxxxxxx++xxXXXXXxx+xxxx+x+x+xx+;::.:;;+xxxxxxxXxXx++xx++xxxxxx+;xxxxxXXx+;::++$&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&x++::;;++++++++;++++++xxxxxxxxxxxXxxxxxxxxxxx++;+xxxx+++::...;++++XxxXXxxxx+++++xxx+xxxxXXxxXxx++::;+x&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&Xx+::::++x++;+;;;+;+++x+xxxxxxxxxxXXXxxxXxxx++++++++xx++;:::;+++xxxxxxxXxXxxx+++xxxxXxxXXXxxx+x+;:;++X&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&xx;:.:+++x++;;;;;+++xx++++xxxxxxxxXxXxxXxxxx++++++xxxx+;;;;;++xxxxxx+;xXxxxxxxxxxXxxXXXXXxxxx+++;;+x&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&Xx+;::;+++++++;;++xxxx+;++x+xxxxxXXxxxxxxx++xXxxxxx+++;++++++x+x+xxxxxxxXxXxxXX++xxxXXxxXXXXxxx++++X&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&xxx++;++x++;;+;++xxXx;++xxxxXxxXXxxXXXxxxxxxxXXxxxxx+x+++++xxxxxXxxxxXXx+xxxxXxxxxxxxXxxXxxxxx++xx&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&xx++++++x+;++++++xxxxxxxxxxXxxXXXXxxxxxxxxxXxXxxx++;+;xx+xxXxxxXxxxxxxXx+xXxx+xxxxxxxxxx++xxx+x+$&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&$xxx++++++x++x++++xxxxxx+x+xxxXxxxxxxxxxxXXxxxxxx++;+xx+xxx++++xXXXXxXxxxxx++xxxxx++xx++xXXXxxX$&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&XxXxxx++++++x++++++xxxx+xxx++xxxxxXXxxxx++xxxXxxx+xxxx++x+xxxXXxXxxx+xxXXx++++xxx+++x+xxXXxXx$&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&$xxxxxx+x++x++;;+++++++++;+xxXxx++x+++:;+xxxxxx++x+xxxx+++xxxxxxx+++xXxx+xx+x+xx++++++xXXxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&$x+++x++xxxx+++++++xx+++;;+xxXxxxx+x+xxxxXxxx++x+xxxx++++xxxxxxx+x+xXxxxxxXx++;;;;+xxxxxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&x+++++xx+;;;+++++++++;::;+xXXxxxxXxxxxxxxx++xxxxxx+xxxXx++xXXxxXXXXxxxxxx+;;;:;+xxxx+x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&X+;+++++;::;;++x++++++;++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXxx+++++++xXXx+x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&X++++++;;;;;;+xx++++++++xxxxxxxxxXxxXXXxxxxxxxxx+xXxXXXXxxxxXXXXXX$$XXxXxxxxx+xXx$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&x;++++;;;;;;+xx+++x++++xxxxx++xxxxxxxxxxxxxXXXxxXxXXXXXXXxxXxxXXXXX$XXxxXXXxxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&$x+;;+++;++;++xxx+x++xxXxxxxxxxxx+xxxXxxxXXXXxXXxxXXxXx+xxxxxXXXXXxxxXXxXxx&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&$+;;++;;;++++++++xxxXxxxxxxxxx+;;+xxXXXXXXXX$XXXXxXx+xxxxXXXXxxxxxXXxxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&++++++++++x++++xxxXxxxxx++;:;+xxxXXXXXXxxXxxx+xxxxxxxXXXXXx+++++xx&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&X+;;+x+xxxxxxxxxxxXXXxxx+;;++xXXXXXx+XXXxxxxxxxxxxxxxxxx+xXXxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$x+;;+++xxxxxxxxxXXXXXxxxxxxxx++;;xxxxxxxxxx+++x++xxXXXXX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&x+;;;+xxxxxXXXxxx++xxxXXXXXxxxXXxxxxx+x+;+++xxxxxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$+;;+++xxxxxxxx+xxxxxxxXxxxx++x+xx++++;+xxx&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$x++xxxxxx+++xxxXXxXXxxxxx++++xxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$XXxxx++++++++xxxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Subrocky
        GameOutput.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$XXXXXXXxxxxxxxXXXXXXX$$$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XXXXXXXXXxxxxXXXXxxxxxxxxxxxxxxxXXXXXXXXXXX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XxxxXXXXXXXxxxxxxxx+++xxxxxxxxxxxxxxxxxxxxxxxxxxxxXxXXX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XxXXXXXXXxxxxxxxx++++++++++++++xxxxxxxxx++++++++xxxxxxxxxxxxxxxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XXXXXXXxxxxxxxxxxx++++++++++++++xxxxxx++xxx+xx++++++++xxxxxxxxxxxxxxxxxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&XxxXXXXxxxxxxxxxx++++++++++++++++x+++++xxxxxxxxxxxxxxxx+xxxxxxxxxxxxxx+xxxxxxxxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$xxXXXxxxxxxxxxxxxx++++++++++++++++++++++++xxxxxxxxxxxx++++xxxx++xxx+xxxxxxxxxx+xxxxx+x$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XxxXxxxxxxxxxx+++++++++x++++++++x++++++++++++++xxxxxxxxxxxxx++++x+++++++++++xxxxxxxx++xxxxxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$XXxxxXxxxxxxxxxxx+++++++++++++++++++++++++++++++++xxxx++++xxx+++++++++++++++++++++xxxx++++xxxxxxxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$xxxxxxxxxxxxxxxxx+++++++++++++++++++++++++++++++++++++++xxxxxxx++++++++++++++++++++xxxxxx++xxx++xxxxxxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Xxxxxxxxxxxxxxxxx+++++++++++++++++++++++++++++++++++++++++++xxxxx+++++++++++++++++++++++x++++++++x++++xxxxxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$Xxxxxxxxx+xxxxxxxx+++++++++++++++++++++++++++++++++++++++++++++x++xxxxxx++++++++++++x+++++xx++++++xxxxxx++xx+xxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$Xxxx+xxx+xxxx+++xxx+++++++++++++++++++++++x+++++++++++++++++++++xxxxxxxx+xxxx++++++++++++++xx+++++++++++++++++++++xX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Xxxx+++++xxxxxx++xxx+++++++++++++++++++++++++++++++++++++++++++++++xxxxxxxxxx++xxx++++++++++++++++++++++++++++++++++++xx&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$Xxx+++++xxxxxxxxxxxx++++++++++++++++++++++++++++++++++++++++++++++++++xxxxxxxxxxx++x+++++++++++++xx++++++++++++++++;+++++xxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Xxxx++++xx+xxx+xxxxxxx+++++++++++++++++++++++++++++++++++++++++++xx+++xxxxxxxxx++xxxxx+++++++++++++++++++++++++++;;+++;;++++xxxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$x++++++++xxxxxxxxxxxx+xxx+++++++++++++++++++++++++++++++++++++++++++++xxxxxxxxxxxxxxxxxxxx+x+x++++++x++++++++++++++;;;+++++++++xxx$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&X++++++xx+++xxxx+xxxxxxxxx++++++++++++++++++xxx++++++++++++++++++xx+++xxxxxxxxxxxxxx+xx+xxx++xxx++xxxxx++++++++++++++;;;;+++++++;+xx+X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$+++++++xxxxxxxxxxx+++xxxx+++++++++xx++++++xxxxx+++x++++++++++xx+++++x+xxxxxx++++++xxx+xx++xxx++++++++++++++++++++++++++++++;++++++;+++++$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&X++++++xxxxxxxxxxxxxxxxxxx++++++++xxxx+++++xxxxxxx++x+xx++++++++++++++xxxxxxxx+++++++xx++xx++++++++++;;;++x+++++++++++++++++++;;++;;;;+++x+X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&x++++++xx++x+xxxx++xxxxxx++xxxxxx+xxxx++xxxxxxxxxxxxxx++++x+++++++xxx++xx++xxxxxxxxX++xxxxxxxxx++xxxxxx+++++++++++++++++++++++++++++++;;+++++x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&$x+++++xxx++x+++x+++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+x+++++xxxxx+xxx+++xxxxxx+++;;;++xxxxxxxxxxxxx+xxxxxxxx++;++++;;;;++++++++++++;;;;;;;++;++x&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&$++++x++x+++x++xxxxxxXxxxxxxxxXxxxxxx+xxxxxxxxxxxxxxXXxxxxxxxxxxxxx+++++++x++++xxxxxxxxxxxxxxxxxxxxxx++++++xxxxxxxxxx+++++++++++++;;++++++;;;++;++x&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&$++++xx++++++xxx++++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xx+xxxxxxxxx+++++++xxxxxxxxxxxxxxxxxxxxxxxxxx+++xx++++++++++++++++++++++++++++;;;;;+++;++++++x&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&++++++++++++xx++++x++x+xxxxxxxxxxxxxxxxxxxXXxxxxxxxx+xxxxxxxx+xxxxxx+++++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++xxx+++++++++++++xxxx++++++++++++++++++++++++X&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&$++++xx+++++++++++x++++xx+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxx++xx++++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+++++++xxxxxxxx++++++++++++++;++++++++xX&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&$;++++++++++x++++++xxxxx++xxxxxxxxxxxxxxxxxXxxxxxxxxxxxxxxxx++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++xxxxxxxxxxxx++++xxxxx+++xxxxxx++++++++;++;;;;++++++++x$&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&++++++x++xxxxxxx++++++++++xxxxxxxxxxxxxxxxxxxxxxXxxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+++xx+++++++xx++++++++++;;+;;++++++++++X$&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&x++++x+xxxxxxxxxx+++++++++xxxxxxxxxxxxxxxxxxxxxXXXxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+x+xxx+xx+++xxx+++++++++;++;;;;;;++++++x++X$&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&X++++++++xxx+xxxxx+xx+++xx++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxxxxxxxxxxxxx++xx+xxxx++++xx++;+;++++++;;;;+++++++xx+xX&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&$+++++++xxxxxxxxxx+xxx++xxxxxxxxxxxxxxxxxxxxxxxXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++xx++++xxxxxx++++++++++++++++++++;;;++xx+X$&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&x+++x++xxx++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++++xxxxxxxxxxx+++xxxxxxxxxxx++++++++++++++x+++++++++++;++++;;;++x++X$&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&$+xxxx++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++xx+++xxxxxxxxxx++++++++++xxxx+++x++++++++++++;;;++++x$&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&x+xxxxxx++xxxxxxxxxxxxxxxxxxxxxxxxxxxXXXxxxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++xxxxxxx++++xx++xxxxxxxxxx+++++++++++xxx+xx+++++++++++;+++;+++x$$&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&XxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxxxxxXXXXxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++++++++++++xxxx+++xx++x++++++++++++++x++x+++++++++++++++++++xX$&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&xxxxxxxxxxxxxxxxxxxxxxxXXXxxxXXxxxxxxxXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxxXx+++xxxxx++++xxxxxx++++++++++++++++++++++++++++++++++++++xXX$&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&X+xxxxxxxxxxxxxxxxxxxxxxxxxxxXxxxxxxxxxXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxxxxxxxxx++++++++++++++xx+++++x++++;+++++++++++xX$&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxxxxxxxxxxxxx++xxxxxxxxxxxxxxxxxxxx++x+++++++x++xxxxxxxxx++++++++++;;++++;++xX$&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&$+xxxxxxx+xxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXxxxXXxxxxxxxxx+xxxxxxxxxxxx++xx+++xxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxxxxxxx+++++xx++xxxxxxxx+++++++++++++++++++xXX$&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&x+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXxXxxxxxxx+xxxxxxxxxxxxxxxxxxxxx++++xxxxxxxxxx+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++++++++++++xxxxxXx++++++++++++++++xxxXX$$&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&++xxxxxxxxxxxxxxxxxxXxxxxxxxxxxxxxxXXXXXXX$$$$XXXxXxxxxxx+xxx+xxxxxxxxxxxxxxxxx+++x+++xxxxxx++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++++++++++xxxXxxx++++++++xx++xxxxXXX$&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&++xxxxxxxxxxxxxxxxXXXXXXXXXXxxxxxXXXXXXXXXXX$$XXXXXxxxXxxx+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++xxxx+xxXXXxxxx++++++xxxxxxXXX$&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&$+xxxxxxxxxxxxxxxxxXXXXXXXXXXXxxxXXXXXXXXXXX$$$XXXXxxxxxxxX+++xxxxxxxxxxxxxxxxxxxxxxxXxxxxxxxxxxxxxxxxxxx++xxxxxxxxxxxxxxx++xxxxxxxxxxxxx++++xxxxxxxxXXxxxxxxx+++xxxXXXxXXX&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&XxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXxxxxxXXXx++xxxxxxxxxxxxxxxxxxxxxxxXXXXxxxxxxxx++++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++++xxxxxxxxxxxxxxxx+++xxxxxXXxXXX$&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&XxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XXXxxXXXXXXxx+xxxxxxxxxxxxxxx++x+x++xXXXXXXXXXxxxxxxXXxxxxxxxxxxxxxxxxxXXxxxxxxxxxxxxxx++++++xxxxxxxxxxxx+xxx++xxxxxXXXxXXX$&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&XxxxXxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXXXXxxxXXXXxXXxxxxxxxxxxxxxxxxxxxxxxxXXX$$XX$XXXXXxxxXXxxxxxxxxxxxxxxxxXXXXxxxxxxxxxxxxx+++++xxxxxxxxxxxxxxxxxx+xxxxXXXXXXXXX&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&XxXxXxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XxXxxXxxXXXXXXxxxxxxxxxxxxxxxxxxxxxxXXxXXXXXxXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxXxxxxxxxxxxxxxxXXX$$XXXX&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&XXxXXxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxXXXXXXXxxxxxxxxxxxxxxxxXXxXxXxxXxxxxXXXXXXXXXXXXxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxxxxxxxxxxxXxXxXXXXXX&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&XXxXXxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXXXXXXxxxXXXXXXXxxxxxxXXxxxxxxxxxxxxxxXxxXxXXXXXXXXXX$$XX$XxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxxxXXXX$&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&$XxXXXxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxXXXXXXXXXxxxxxxXXXXXxxXXxXXXXXXXXXXXXXXXXX$$$XXXXXxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxXxXXXX$&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&$XxXXXxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXX$$$XXXXXXxxXxxxXX$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$$$X$$XXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxXXXXXXx$&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&$XXXXXxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XXXXXXXXXXXXXXX$$$XXXXXXXX$XXXXXXXXXXXXXXXXXXXXXXX$$XXXXXXXXX$$XXXXXXXXXXxxXxxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXX$&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&XXXXXxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$XXXXXXXX$XXXXX$$$$$$$XXXXXXXX$$$XXXXXXXXXXXX$$XXXXXXXXX$$$XX$$$XXXXXXXXXXXXXXxxXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXX$$XXXX&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&XXXXXXxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$XXXXXXX$XXXXXXXXXX$$$XXXXXXXXXX$$XXXXXXXXXX$$XXXXXXX$$$$XXX$$$$$XXXXX$XXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXX$$XXXX&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&XXXXXXXXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XXXXXXXXXXXXXXXXXXX$$$$$$XXXXXXXX$$$XXXXXXXX$$$XXXXXXXXXXX$X$$$$$$$$$$$$$$XXXXXXXXX$XXxxxxxXxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXX$&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&$XXXXXXXXXXXxXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XX$$XXXXXXXXXXXX$$XXX$$$$XXXXXXXXXXXX$XXXXXXXXX$$$$$$$$$$$$$XX$$$$$$$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxXxxxXXXXX$&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$$XXXXXXXXXXXX$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$$$$$X$$$$$$$$$$$$$&$$$$$$$$XXXXX$$$$$XXXX$$XXXXXxxxxxxxxxxxxxxxxxxXxxXXXX$&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$$X$$$$$$$$$$$$$$$$$$$$$XX$$XXXXXXXXXXX$XXXXXXXxxxxxxxxxxxxxxXxxXXXXXXX$&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$$$X$$$$$$X$$$$$$$$$$$$$XX$$XXXXXXXXXXXXXXXXXXXXXxXXxxxxxxxxxxxXXXXXXxXX&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XXX$XXXXXXXXXXXX$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxXXXXXXXX$XXX$&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxXXXXXXXXXXXXX$$XXXXXXXXXXXXXXXXXXXX$XXXXXXX$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$$XXX&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&$XXXXXXXXXXXXXXXXXXXXXXXXXXXxXXxxXXXXXXXxxxxXXXXxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$XXXX$&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&$X$XXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxXXXXXxxxxxxXXXXXXXxxxxxxxxxxxxxxxXxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxX&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&$XXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxXXXXxX$&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxXXXXXXXXXXXXXXXXXXXXXXXXXXXx$&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXxxxxxxxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxXXXXxXXXXXXXXxXXXXxXXXXXXXXXXXXxxxXXxxXXxxX&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXxxxXXXXXXXXXXXXXXXXXxxxXXXXxxxxxxxxxXXXXx&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxXXXXXXXXXXXXXXXXXxxxxxXxxxxxxxxxXxxXx$&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxXXXXXXXXXXXXXXXXXxxxxxXxxxxxxxxxxxxx$&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXxxXXXXXXXXXxXXXXXXXXXXXXXXXXXxxxxxxXXxxxxxxxxxXX$&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXxxxxxxxxxxxxxxxxxxXXXxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxXXXXXxxxxxxXXXXx$&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXxxxxxxxxxxxxxxxxxxXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxXXXxxXXXXxxxxx$&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXxxXXXXXXXXXXXXXXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxXXXXXxxxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXXXXXXXXXXXXXXXXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxXXXxxXXXXXXXXxX&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXXXXxXx+X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx+X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXxXxxxxxXXXXXXXxXXXXXXXxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx+++&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$XXXXXXXXXXXXXXXXXxxxxxxxXXXXXXXXXXXXXXxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXxxXXXxxxXXXXxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx+;;x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxXXXXXXXXXXXXXXXXXXXXXXxxxXXxxxxxxxxXXxxxXXXxxxXXXXXXXXXXXXXXXXXXXXXXXxx+;:;$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxXXxxxXXXXxXXXxxXxxXXXXXXXXXXXXXXXxxxxxx+:.:X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxXXXxxxxxxxxxxxxxxXXXXXXXXXXXxxxxxxxx++;:..x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxXXxxxxxXXXXxxxxxxxxxxxxxxxxxxxxxxxXXXxxxxxxxxx+;:.:x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxXXXXXxxxXxxxxxxxxxxxxxxxxxxxxxxxxxx+xxx+xx++:.:+&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxXXXxXXXxxxxxxxxxxxxxxxxxxxxxXx++++xxxxx+;:.:x&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxXxxxxxxxxxxx+xxxxXxxxxx+xxxxx+++;:.:;X&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxx+xxxxx+++xxxxxxxxxxx++xxx+;;:::x$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$XXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+x+xx++x+;::+$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$$$$XXXXXXXxxxxxxXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+++xx+++;;+$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$$$XXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx;;+xXxxxxxxxxxx+x$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$$XXXXXXxxXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXxxXxxXxX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$&&$$$$$&$$$$$$$$$$$XXXXxxXXXXXxxXXxxxxxXXXXXXxXXXXXXXXXXXXXXXX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$X$$$$XXXXXXXX$$XXXXXXXXXXXXXXXXXXXXX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$X$$$$$XXXXXXX$$$$XX$XXXXXXX$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$$$XXX$$$XX$XXXX$$$XXX$XXXXXXX$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n" + //
        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        */
        menu.startMainMenu();
        System.exit(0);
    }

    public static void generateUniverse(){
        ArrayList<Star> systemSave = new ArrayList<>();
        ArrayList<Exoplanet> orbitingPlanets = new ArrayList<>();
        for (int i = 0; i < 64; i ++)
        {
            for (char n = 'A'; n <= 'G'; n ++)
            {
                String name = n + "-" + (((int)n - (int)(Math.random() * 17))) + "." + i;
                double mass = getMass();
                String type = getType(mass);
                int planets = (int)(Math.random() * 8) + 1;
                Star saveStar = new Star(mass, Math.pow(mass,0.8), name, type, getColour(type), planets);
                for (int j = 0; j < planets; j ++)
                {
                    double planetMass = Math.pow(10, (int)(-(Math.random()*6)) - 1);
                    String planetName = name + "-" + (char)('A' + j);
                    String planetType = getPlanetType(planetMass);
                    String planetAtmosphere = getAtmosphere(planetMass);
                    int planetTemperature = (int)(Math.random()*512);
                    boolean planetLife = getLife(planetType, planetAtmosphere, planetTemperature);
                    ArrayList<String> resources = new ArrayList<>(Resources.planetResources(planetType, planetLife));
                    Exoplanet planetSave = new Exoplanet(planetMass, Math.pow(planetMass, 0.8), planetName, planetType, planetLife, getMoons(planetMass), planetTemperature, planetAtmosphere, saveStar, new ArrayList<>(resources));
                    orbitingPlanets.add(planetSave);
                    resources.clear();
                }
                saveStar.createSystem(new ArrayList<>(orbitingPlanets));
                orbitingPlanets.clear();
                systemSave.add(saveStar);
            }
            universe.add(new ArrayList<>(systemSave));
            systemSave.clear();
        }
    }
        

    public static void initialize()
    {
        for (int i = 0; i < map.length; i ++)
        {
            for (int n = 0; n < map.length; n ++)
            {
                for (int j = 0; j < map.length; j ++)
                {
                    map[i][n][j] = j + (4
                        *n) + (16*i);
                }
            }
        }

        playerX = 0;
        playerY = 0;
        playerZ = 0;
    }
    // end initialize

    public static void printMap()
    {
        for (int i = 0; i < map.length; i ++)
            {
                System.out.println("Row " + (i + 1) + ":");
                for (int n = 0; n < map.length; n ++)
                {
                    System.out.println("Box " + (n + 1) + ":");
                    for (int j = 0; j < map.length; j ++)
                    {
                        System.out.print(" " + map[i][n][j] + " ");
                    }
                    System.out.println();
                }
            }
    }
    // end printMap

    public static double getMass()
    {
        double seed = Math.random();
        return (Math.pow(seed, 4)*100000) + 80.0;
        // second int in Math.pow reflects the distribution of masses. Higher numbers lead to more light stars on average, while lower numbers lead to more consistent distribution of stars
    }
    // end getMass

    public static String getType(double mass)
    {
        String type = "";
        double decimal;
        if (mass < 700)
        {
            type += "M";

            decimal = (mass / 700) * 10;
        }
        else if (mass < 840)
        {
            type += "K";
            decimal = ((mass - 700) / 140) * 10;
        }
        else if (mass < 1090)
        {
            type += "G";
            decimal = ((mass - 840) / 250) * 10;
        }
        else if (mass < 1470)
        {
            type += "F";
            decimal = ((mass - 1090) / 380) * 10;
        }
        else if (mass < 2200)
        {
            type += "A";
            decimal = ((mass - 1470) / 730) * 10;
        }
        else if (mass < 16700)
        {
            type += "B";
            decimal = ((mass - 2200) / 14500) * 10;
        }
        else
        {
            type += "O";
            decimal = ((mass - 16700) / 83300) * 10;
        }

        return type + (decimal + "").substring(0, 3); // If possible, fix rounding
    }
    // end getType

    public static String getColour(String type)
    {
        String letterType = type.substring(0, 1);

        if (letterType.equals("O"))
        {
            return "Violet-White";
        }
        else if (letterType.equals("B"))
        {
            return "Blue-White";
        }
        else if (letterType.equals("A"))
        {
            return "White";
        }
        else if (letterType.equals("F"))
        {
            return "Yellow-White";
        }
        else if (letterType.equals("G"))
        {
            return "Yellow";
        }
        else if (letterType.equals("K"))
        {
            return "Orange";
        }
        else
        {
            return "Red";
        }
    }
    // end getColour

    public static String getPlanetType(double mass)
    {
        if (mass > 0.3)
        {
            return "Gas Giant";
        }
        else if (mass > 0.03)
        {
            return "Ice Giant";
        }
        else if (mass > 0.003)
        {
            return "Superrocky";
        }
        else if (mass > 0.0003)
        {
            return "Rocky";
        }
        else
        {
            return "Subrocky";
        }
    }
    // end getPlanetType

    public static int getMoons(double mass)
    {
        if (mass > 0.03)
        {
            return (int)(Math.random()*10);
        }
        else
        {
            return (int)(Math.random()*3);
        }
    }
    // end getMoons

    public static String getAtmosphere(double mass)
    {
        if (mass > 0.03)
        {
            return "Incredibly High";
        }
        else
        {
            double randomAtmosphere = Math.pow(Math.random(), 3);
            if (randomAtmosphere > 0.8)
            {
                return "Incredibly High";
            }
            else if (randomAtmosphere > 0.6)
            {
                return "High";
            }
            else if (randomAtmosphere > 0.4)
            {
                return "Medium";
            }
            else if (randomAtmosphere > 0.2)
            {
                return "Minimal";
            }
            else
            {
                return "Incredibly Minimal";
            }
        }
    }
    // end getAtmosphere

    public static boolean getLife(String type, String atmosphere, int temperature)
    {
        if ((223 < temperature && temperature < 323) && atmosphere.equals("Medium") && type.equals("Rocky") || type.equals("Superrocky") || type.equals("Subrocky"))
        {
            return Resources.chance(20);
        }
        else
        {
            return Resources.chance(1);
        }
    }
    // end getLife

    public static String surroundings()
    {
        String surroundings = "";
        int position = getPosition();
        if (playerShip.getScanLevel())
        {
            for (int i = 0; i < 7; i ++)
            {
                surroundings += universe.get(position).get(i).scan();
                surroundings += "\n";
            }
        }
        else
        {
            for (int i = 0; i < 7; i ++)
            {
                surroundings += universe.get(position).get(i).basicScan();
                surroundings += "\n";
            }
        }
        
        return surroundings;
    }
    // end surroundings

    public static int getPosition()
    {
        return (playerX + 4*playerY + 16*playerZ);
    }
    // end getPosition

    public static boolean movement(int moveType)
    {
        if (playerShip.getFuel() <= 100.0)
        {
            GameOutput.println("Not enough fuel.");
            return false;
        }
        
        if (moveType == 0)
        {
            if (playerX != 3)
            {
                playerX++;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 1)
        {
            if (playerX != 0)
            {
                playerX--;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 2)
        {
            if (playerY != 3)
            {
                playerY++;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 3)
        {
            if (playerY != 0)
            {
                playerY--;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 4)
        {
            if (playerZ != 3)
            {
                playerZ++;
            }
            else
            {
                return false;
            }
        }
        else if (moveType == 5)
        {
            if (playerZ != 0)
            {
                playerZ--;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        playerShip.useFuel(100);
        return true;
    }
    // end movement

    public static ArrayList<Star> getSector()
    {
        return universe.get(getPosition());
    }
    // end getSector

    public static Star choiceStar;
    public static Exoplanet choicePlanet;
    private static boolean choicePlanetScanned;

    public static void setChoiceStar(Star input)
    {
        if (choiceStar != input)
        {
            choicePlanet = null;
            choicePlanetScanned = false;
        }

        choiceStar = input;
    }
    // end setChoiceStar

    public static Star getChoiceStar()
    {
        return choiceStar;
    }
    // end getChoiceStar

    public static void setChoicePlanet(Exoplanet input)
    {
        if (choicePlanet != input)
        {
            choicePlanetScanned = false;
        }

        choicePlanet = input;
    }
    // end setChoicePlanet

    public static Exoplanet getChoicePlanet()
    {
        return choicePlanet;
    }

    public static void setChoicePlanetScanned(boolean scanned)
    {
        choicePlanetScanned = scanned;
    }

    public static boolean getChoicePlanetScanned()
    {
        return choicePlanetScanned;
    }
    // end getChoicePlanet

    public static int playerLandX;
    public static int playerLandY;
    public static String[][] land;

    public static void landing(String type, Exoplanet input)
    {
        if (type.equals("Superrocky"))
        {
            land = new String[5][5];
            
        }
        else if (type.equals("Rocky"))
        {
            land = new String[4][4];
        }
        else
        {
            land = new String[3][3];
        }
        ArrayList<String> resources = new ArrayList<String>(input.getResources());

        for (int i = 0; i < land.length; i ++)
        {
            for (int n = 0; n < land.length; n ++)
            {
                if (Resources.chance(25) && !resources.isEmpty())
                {
                    land[i][n] = resources.get(0);
                    resources.remove(0);
                }
                else
                {
                    land[i][n] = "-";
                }
            }
        }
        playerLandX = 0;
        playerLandY = 0;

        input.modifyResources(new ArrayList<String>(resources));
    }
    // end landing

    public static void landingMovement(int status)
    {
        if (status == 0)
        {
            playerLandX ++;
            if (playerLandX == land.length)
            {
                playerLandX = 0;
            }
        }
        else if (status == 1)
        {
            playerLandX --;
            if (playerLandX < 0)
            {
                playerLandX = land.length - 1;
            }
        }
        else if (status == 0)
        {
            playerLandX ++;
            if (playerLandX == land.length)
            {
                playerLandX = 0;
            }
        }
        else if (status == 1)
        {
            playerLandX --;
            if (playerLandX < 0)
            {
                playerLandX = land.length;
            }
        }
        else if (status == 2)
        {
            playerLandY ++;
            if (playerLandY == land.length)
            {
                playerLandY = 0;
            }
        }
        else if (status == 3)
        {
            playerLandY --;
            if (playerLandY < 0)
            {
                playerLandY = land.length - 1;
            }
        }

        if (!land[playerLandX][playerLandY].equals("-"))
        {
            int amount = (int)(Math.random()*2) + 1;

            inventory.addItem(land[playerLandX][playerLandY], amount);

            GameOutput.println("You found " + amount + " units of " + land[playerLandX][playerLandY] + "!");

            land[playerLandX][playerLandY] = "-";
        }
    }
    // end landingMovement

    public static String getLandingPosition()
    {
        return "Your position:" + playerLandX + ", " + playerLandY;
    }
    // end getLandingPosition
}
// end Main
/*
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
..........................................................:-==+***************++++==--:.............................
.............................................:=**+--=-=============:-#%%%%%%%%%%%%%#######*+**=:....................
.....................................-=++*##%+============+++++++++++=+--%@@@@@@@%#*=--=+#%%%%####*++:..............
...............................-+++####%%+-=+++++++++++++++++**++++++++*+=-=%%@@@@@@@@@@@@@%=:-*%%%###++*-..........
..........................=++*###%*-:#%@%+++++*******************+***###*+++-.####%%@@@@@@@@@@@@@=:=%%%###+*=.......
.....................:+++*#%#+-=%@@@@@@=+*#***********************######***+++-.:--=*###%%%@@@@@@@@@#--#%%##**+.....
..................*+*##%@=.%@@@@@@@@%*++#########**********############*####+++=...:--:--*##%%@@@@@@@@@=-+@%##**+...
..............-++**%%+-*@@@@@@@%%##*++#######################################*++=......::::-+##%%@@@@@@@@--%%%##+#..
...........-*+*##%=:%@@@@@@@%##*=:.-+*#######################################**++=.......:.-.-+##%@@@@@@@@=-%%###+*.
.........+++##%#:+@@@@@@@%##+-:::.-+*##########################################*+=-........:::--###@@@@@@@@:=@%##+#:
.......++*##%+:%@@@@@@%###--::....++###########################################+++=..........-.-+##%@@@@@@@--@%#**#-
.....=+**#%*:#@@@@@@%###--::.....:+*#########################################**+**=:........::::=##%@@@@@@@:-@%#*+#.
....#+*#%@--@@@@@@@%##=:-::......=+*#######################################***###+=-........:-.-###%@@@@@@#:#%##**-.
...#+*##@-=@@@@@@@%#%-:::........=++*###############################***#***#####*+=:.......:-.-*##@@@@@@@%.*%#*#+-..
..*+**#@=:@@@@@@@%##+-:::........=+##****#####################*******###%%%%###**+=:.....::-:-##%@@@@@@@=-%%#*+*:...
.-#*##%@-=@@@@@@@%#%-:::.........:+*#####**********************####%%%%%%######*++=.....:::-###@@@@@@@#.+%#*+++.....
.-##*#%@-=@@@@@@@%##*-::..........=+*##%%%%%%%%%%%%%%%%%%%%%%@%%%%%%%%########**++-..::::=##%%@@@@@@#:#%#*++=.......
..#+###@=:%@@@@@@@%##+-::::.......:+**#####%%%%%%%%%%%%%%%%%%%%%%%############*+=-..::=###%@@@@@@@--%%#+*+-.........
..=***%%@=:@@@@@@@@%%##=-:-::......-+*########%%%%%%%%%%%%%%%%%%##########**+====:-+##%@@@@@@@@=-#%#**+=............
...=#+##%@+-+@@@@@@@@@%#%#=::::::...-+*##########%##%%%%%%%%%%%######*+++===++*##%%@@@@@@@@#:+%%#*++=...............
.....#+##%%@*--@@@@@@@@@@%####=-::::.:===+++****#######*****+++++++==++*####%%@%@@@@@@@+.+@%#**++...................
.......++*##%%%+-=%@@@@@@@@@@@%%####++==+++++++++++++++++++++***#####%%%@@@@@@@@@%+-=#%%#*++=:......................
..........#+*##%%@%=:-%@@@@@@@@@@@@@@@%%%%%%############%%%%%%%%@@%@@@@@@@@%=.=#%###*+=+:...........................
.............-***##%#%%%#=--+%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#+=++#######++++-.................................
..................=++*#####%%%@%%*+--:--=+++****###**+++++##%%%%%%###*+++==-........................................
..........................-+*#*++#########################*****+=-..................................................
.........................................::::.......................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
....................................................................................................................
.................................................................................................................... */