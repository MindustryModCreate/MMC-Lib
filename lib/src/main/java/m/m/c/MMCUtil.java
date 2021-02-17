package m.m.c;
import android.graphics.Color;
import java.util.HashMap;

public class MMCUtil {

    private static final HashMap<String, Integer> map = new HashMap<String,Integer>();

    public static int test = 0;

    public static class Colors{

        public static int toHEX(String hex) {
            int r = Integer.valueOf( hex.substring( 1, 3 ), 16 );
            int g = Integer.valueOf( hex.substring( 3, 5 ), 16 );
            int b = Integer.valueOf( hex.substring( 5, 7 ), 16 );
            return Color.rgb(r,g,b);
        }

        public static String toRGB(int rgb) {
            int r = ((rgb >> 16) & 0xff);
            int g = ((rgb >> 8) & 0xff);
            int b = (rgb & 0xff);
            return String.format("#%02X%02X%02X", r, g, b);
        }

        /// Colors of Mindustry
        /// Цвета из Mindustry
        public static final int white = m.m.c.MMC.toHEX("#ffffff");
        public static final int lightGray = m.m.c.MMC.toHEX("#bfbfbf");
        public static final int gray = m.m.c.MMC.toHEX("#7f7f7f");
        public static final int darkGray = m.m.c.MMC.toHEX("#3f3f3f");
        public static final int black = m.m.c.MMC.toHEX("#000000");
        public static final int clear = m.m.c.MMC.toHEX("#00000000");
        public static final int blue = m.m.c.MMC.toHEX("#0000ff");
        public static final int navy = m.m.c.MMC.toHEX("#00007E");
        public static final int royal = m.m.c.MMC.toHEX("#4169e1");
        public static final int slate = m.m.c.MMC.toHEX("#708090");
        public static final int sky = m.m.c.MMC.toHEX("#87ceeb");
        public static final int cyan = m.m.c.MMC.toHEX("#00ffff");
        public static final int teal = m.m.c.MMC.toHEX("#007E7E");
        public static final int green = m.m.c.MMC.toHEX("#00ff00");
        public static final int acid = m.m.c.MMC.toHEX("#7fff00");
        public static final int lime = m.m.c.MMC.toHEX("#32cd32");
        public static final int forest = m.m.c.MMC.toHEX("#228b22");
        public static final int olive = m.m.c.MMC.toHEX("#6b8e23");
        public static final int yellow = m.m.c.MMC.toHEX("#ffff00");
        public static final int gold = m.m.c.MMC.toHEX("#ffd700");
        public static final int goldenrod = m.m.c.MMC.toHEX("#daa520");
        public static final int orange = m.m.c.MMC.toHEX("#ffa500");
        public static final int brown = m.m.c.MMC.toHEX("#8b4513");
        public static final int tan = m.m.c.MMC.toHEX("#d2b48c");
        public static final int brick = m.m.c.MMC.toHEX("#b22222");
        public static final int red = m.m.c.MMC.toHEX("#ff0000");
        public static final int scarlet = m.m.c.MMC.toHEX("#ff341c");
        public static final int crimson = m.m.c.MMC.toHEX("#dc143c");
        public static final int coral = m.m.c.MMC.toHEX("#ff7f50");
        public static final int salmon = m.m.c.MMC.toHEX("fa8072");
        public static final int pink = m.m.c.MMC.toHEX("#ff69b4");
        public static final int magenta = m.m.c.MMC.toHEX("#ff00ff");
        public static final int purple = m.m.c.MMC.toHEX("#a020f0");
        public static final int violet = m.m.c.MMC.toHEX("#ee82ee");
        public static final int maroon = m.m.c.MMC.toHEX("#b03060");

    }

    static{
        reset();
    }

    public static HashMap<String, Integer> getColors(){
        return map;
    }

    public static int get(String name){
        return map.get(name);
    }

    public static void reset(){
        map.clear();
        map.put("CLEAR", Colors.clear);
        map.put("BLACK", Colors.black);
        map.put("WHITE", Colors.white);
        map.put("LIGHT_GRAY", Colors.lightGray);
        map.put("GRAY", Colors.gray);
        map.put("DARK_GRAY", Colors.darkGray);
        map.put("BLUE", Colors.blue);
        map.put("NAVY", Colors.navy);
        map.put("ROYAL", Colors.royal);
        map.put("SLATE", Colors.slate);
        map.put("SKY", Colors.sky);
        map.put("CYAN", Colors.cyan);
        map.put("TEAL", Colors.teal);
        map.put("GREEN", Colors.green);
        map.put("ACID", Colors.acid);
        map.put("LIME", Colors.lime);
        map.put("FOREST", Colors.forest);
        map.put("OLIVE", Colors.olive);
        map.put("YELLOW", Colors.yellow);
        map.put("GOLD", Colors.gold);
        map.put("GOLDENROD", Colors.goldenrod);
        map.put("ORANGE", Colors.orange);
        map.put("BROWN", Colors.brown);
        map.put("TAN", Colors.tan);
        map.put("BRICK", Colors.brick);
        map.put("RED", Colors.red);
        map.put("SCARLET", Colors.scarlet);
        map.put("CRIMSON", Colors.crimson);
        map.put("CORAL", Colors.coral);
        map.put("SALMON", Colors.salmon);
        map.put("PINK", Colors.pink);
        map.put("MAGENTA", Colors.magenta);
        map.put("PURPLE", Colors.purple);
        map.put("VIOLET", Colors.violet);
        map.put("MAROON", Colors.maroon);
        map.put("clear", Colors.clear);
        map.put("black", Colors.black);
        map.put("white", Colors.white);
        map.put("lightgray", Colors.lightGray);
        map.put("gray", Colors.gray);
        map.put("darkgray", Colors.darkGray);
        map.put("blue", Colors.blue);
        map.put("navy", Colors.navy);
        map.put("royal", Colors.royal);
        map.put("slate", Colors.slate);
        map.put("sky", Colors.sky);
        map.put("cyan", Colors.cyan);
        map.put("teal", Colors.teal);
        map.put("green", Colors.green);
        map.put("acid", Colors.acid);
        map.put("lime", Colors.lime);
        map.put("forest", Colors.forest);
        map.put("olive", Colors.olive);
        map.put("yellow", Colors.yellow);
        map.put("gold", Colors.gold);
        map.put("goldenrod", Colors.goldenrod);
        map.put("orange", Colors.orange);
        map.put("brown", Colors.brown);
        map.put("tan", Colors.tan);
        map.put("brick", Colors.brick);
        map.put("red", Colors.red);
        map.put("scarlet", Colors.scarlet);
        map.put("crimson", Colors.crimson);
        map.put("coral", Colors.coral);
        map.put("salmon", Colors.salmon);
        map.put("pink", Colors.pink);
        map.put("magenta", Colors.magenta);
        map.put("purple", Colors.purple);
        map.put("violet", Colors.violet);
        map.put("maroon", Colors.maroon);
    }

}
