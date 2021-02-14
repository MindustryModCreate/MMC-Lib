package m.m.c;
import android.graphics.Color;
import java.nio.charset.Charset;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

public class MMCUtil {
    
	public static class ColorMMC{
		
		public static int parseColorHEX(String hex) {
			int[]rgb={0,0,0};
			try{
				rgb[0]=Integer.valueOf( hex.substring( 1, 3 ), 16 );
				rgb[1]=Integer.valueOf( hex.substring( 3, 5 ), 16 );
				rgb[2]=Integer.valueOf( hex.substring( 5, 7 ), 16 );
			}catch(Exception e){
				rgb[0]=0;
				rgb[1]=0;
				rgb[2]=0;
			}
			return Color.rgb(rgb[0],rgb[1],rgb[2]);
		}

		public static String ToRgb(int argb) {
			int r = ((argb >> 16) & 0xff);
			int g = ((argb >> 8) & 0xff);
			int b = (argb & 0xff);
			return String.format("#%02X%02X%02X", r, g, b);
		}
		
		
	}
	
}
