package mmc.widget;
import android.widget.*;
import android.content.Context;
import android.util.AttributeSet;
import java.lang.*;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.graphics.Color;

public class TextViewMMC extends TextView{
	
	public int t = 0;
	public int s = 1;
	
	private Rect mRect;
	private Paint highlightPaint;
	private boolean draw = false;
	private Context ctx;
	public TextViewMMC(Context context) {
        super(context);
		this.ctx = context;
		mRect = new Rect();
        highlightPaint = new Paint();
        highlightPaint.setColor(0x01f3f304);
	}
	
	public TextViewMMC(Context context, AttributeSet attrs) {
        super(context, attrs);
		this.ctx = context;
		mRect = new Rect();
        highlightPaint = new Paint();
        highlightPaint.setColor(0x01f3f304);
	}
	
	public void setDraw(boolean draw){
		this.draw=draw;
	}
	
	public TextViewMMC setCompoundDrawablesMMC(Drawable l,Drawable t,Drawable r,Drawable b){
		setCompoundDrawables(l,t,r,b);
		return this;
	}
	
	public TextViewMMC setTextMMC(String text){
		setText(text);
		return this;
	}
	
	public TextViewMMC setSetting(Drawable d){
		setBackgroundDrawable(d);
		return this;
	}

	public TextViewMMC setSetting(Drawable d,float size){
		setBackgroundDrawable(d);
		setTextSize(size);
		return this;
	}
	
	public TextViewMMC setSetting(double size){
		setTextSize(Float.valueOf(String.valueOf(size)));
		return this;
	}
	
	public TextViewMMC setColor(String color){
		setTextColor(Color.parseColor(color));
		return this;
	}
	
	public TextViewMMC setColor(int color){
		setTextColor(color);
		return this;
	}
}
