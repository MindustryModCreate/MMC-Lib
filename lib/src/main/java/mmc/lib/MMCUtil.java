package mmc.lib;
import android.graphics.Color;
import java.util.HashMap;

public class MMCUtil {

    private static final HashMap<String, Integer> map = new HashMap<String,Integer>();

    public static final int toColor(String color){
        int[]rgb={0,0,0};
        try{
            rgb[0]=Integer.valueOf( color.substring( 1, 3 ), 16 );
            rgb[1]=Integer.valueOf( color.substring( 3, 5 ), 16 );
            rgb[2]=Integer.valueOf( color.substring( 5, 7 ), 16 );
        }catch(Exception e){
            rgb[0]=0;
            rgb[1]=0;
            rgb[2]=0;
        }
        return Color.rgb(rgb[0],rgb[1],rgb[2]);
    }
    
    public static String toColor(int color){
        int r = ((color >> 16) & 0xff);
        int g = ((color >> 8) & 0xff);
        int b = (color & 0xff);
        return String.format("#%02X%02X%02X", r, g, b);
    }
    
    public static int toColor(int color1, int color2) {
        return color1+color2;
    }
    
    public static Object toColor(String color1, String color2, boolean string) {
        if(string){
            return MMC.toColor(MMC.toColor(color1)+MMC.toColor(color2));
        }else{
            return MMC.toColor(color1)+MMC.toColor(color2);
        }
    }
    
    public static class Colors{

        /// Colors of Mindustry
        /// Цвета из Mindustry
        public static final int white = MMC.toColor("#ffffff");
        public static final int lightGray = MMC.toColor("#bfbfbf");
        public static final int gray = MMC.toColor("#7f7f7f");
        public static final int darkGray = MMC.toColor("#3f3f3f");
        public static final int black = MMC.toColor("#000000");
        public static final int clear = MMC.toColor("#00000000");
        public static final int blue = MMC.toColor("#0000ff");
        public static final int navy = MMC.toColor("#00007E");
        public static final int royal = MMC.toColor("#4169e1");
        public static final int slate = MMC.toColor("#708090");
        public static final int sky = MMC.toColor("#87ceeb");
        public static final int cyan = MMC.toColor("#00ffff");
        public static final int teal = MMC.toColor("#007E7E");
        public static final int green = MMC.toColor("#00ff00");
        public static final int acid = MMC.toColor("#7fff00");
        public static final int lime = MMC.toColor("#32cd32");
        public static final int forest = MMC.toColor("#228b22");
        public static final int olive = MMC.toColor("#6b8e23");
        public static final int yellow = MMC.toColor("#ffff00");
        public static final int gold = MMC.toColor("#ffd700");
        public static final int goldenrod = MMC.toColor("#daa520");
        public static final int orange = MMC.toColor("#ffa500");
        public static final int brown = MMC.toColor("#8b4513");
        public static final int tan = MMC.toColor("#d2b48c");
        public static final int brick = MMC.toColor("#b22222");
        public static final int red = MMC.toColor("#ff0000");
        public static final int scarlet = MMC.toColor("#ff341c");
        public static final int crimson = MMC.toColor("#dc143c");
        public static final int coral = MMC.toColor("#ff7f50");
        public static final int salmon = MMC.toColor("fa8072");
        public static final int pink = MMC.toColor("#ff69b4");
        public static final int magenta = MMC.toColor("#ff00ff");
        public static final int purple = MMC.toColor("#a020f0");
        public static final int violet = MMC.toColor("#ee82ee");
        public static final int maroon = MMC.toColor("#b03060");

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
