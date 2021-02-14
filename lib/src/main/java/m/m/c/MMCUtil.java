package m.m.c;
import android.graphics.Color;
import java.nio.charset.Charset;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

public class MMCUtil {
    
	public static class ColorMMC{
		
		public static int toHEX(String hex) {
			int r = Integer.valueOf( hex.substring( 1, 3 ), 16 );
			int g = Integer.valueOf( hex.substring( 3, 5 ), 16 );
			int b = Integer.valueOf( hex.substring( 5, 7 ), 16 );
			return Color.rgb(r,g,b);
		}

		public static String ToRgb(int rgb) {
			int r = ((rgb >> 16) & 0xff);
			int g = ((rgb >> 8) & 0xff);
			int b = (rgb & 0xff);
			return String.format("#%02X%02X%02X", r, g, b);
		}
		
		
	}
	
}
