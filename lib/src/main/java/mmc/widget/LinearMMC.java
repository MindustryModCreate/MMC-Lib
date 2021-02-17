package mmc.widget;
import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;

public class LinearMMC extends LinearLayout {
    
	public int l = 0;
	
    public LinearMMC(Context context,AttributeSet attrs){
		super(context,attrs);
	}
    
	public LinearMMC setColor(int color){
		setBackgroundColor(color);
		return this;
	}

	public LinearMMC setDrawable(Drawable background) {
		setBackgroundDrawable(background);
		return this;
	}
	
}
