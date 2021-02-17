package m.m.c;

public class Launges{
	//Regex - Регекс
	public static String JavaScript = "\\b(require|abstract|boolean|break|byte|case|catch|char|class|const|continue|debugger|default|delete|do|double|else|enum|export|extends|false|final|finally|float|for|function|goto|if|implements|import|in|instanceof|int|interface|long|native|new|null|package|private|protected|public|return|short|static|super|switch|synchronized|this|throw|throws|transient|true|try|typeof|var|void|volatile|while|with)\\b";
	public static String Hjson = "\\b(true|false)\\b";
	public static String Json = "\\b(true|false)\\b";
	public static String Number = "(\\b(\\d*[.]?\\d+)\\b)";
	public static String Color = "([\"][#]([0-9]|[a-f]|[A-F]){6}[\"]|\\[[#](([0-9]|[a-f]|[A-F]){6})\\])";
	public static String Bracket = "(\"(?:.|[\\n\\r])*?\"|'.*')";
	public static String Comment = "/\\*(?:.|[\\n\\r])*?\\*/|//(.*|[^\n])";
	
    public static String JS = "(var|let|const) (_?[A-Za-z0-9]+)";
	public static String Java = "";
	public static String Kotlin = "";
}
