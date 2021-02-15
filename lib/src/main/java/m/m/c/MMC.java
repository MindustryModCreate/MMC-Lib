package m.m.c;

public class MMC {
    
	/// MMC-Lib version
	/// MMC-Lib версия
    public static String version = "0.06";
    
	/// Color from int to String
	/// Цвет из int в String
	public static int toHEX(String color){
		/// m.m.c.MMCUtil.java
		return m.m.c.MMCUtil.Colors.toHEX(color);
	}
	
	/// Color from String to int
	/// Цвет из String в int
	public static String toRGB(int color){
		/// m.m.c.MMCUtil.java
		return m.m.c.MMCUtil.Colors.toRGB(color);
	}
	
}
