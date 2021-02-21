package m.m.c;

public class MMC {
    
	/// MMC-Lib version
	/// MMC-Lib версия
    public static String version = "0.10";
    
	/// Color from int to String
	/// Цвет из int в String
	public static int toColor(String color){
		/// m.m.c.MMCUtil.java
		return m.m.c.MMCUtil.toColor(color);
	}
	
	/// Color from String to int
	/// Цвет из String в int
	public static String toColor(int color){
		/// m.m.c.MMCUtil.java
		return m.m.c.MMCUtil.toColor(color);
	}
    
    public static int toColor(int color1, int color2){
        /// m.m.c.MMCUtil.java
        return m.m.c.MMCUtil.toColor(color1,color2);
	}
    
    public static Object toColor(String color1, String color2, boolean string){
        /// m.m.c.MMCUtil.java
        return m.m.c.MMCUtil.toColor(color1,color2,string);
	}
}
