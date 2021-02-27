package mmc.widget;
import android.widget.EditText;
import android.graphics.Paint;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.MotionEvent;
import android.graphics.Color;
import android.database.Cursor;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.text.TextWatcher;
import java.util.regex.Pattern;
import android.text.Editable;
import android.text.style.ForegroundColorSpan;
import java.util.regex.Matcher;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import android.content.SharedPreferences;
import android.text.style.StyleSpan;
import android.text.Spannable;
import android.view.textservice.SpellCheckerSubtype;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.text.TextPaint;
import java.util.*;
import android.view.accessibility.AccessibilityEvent;
import android.text.style.BackgroundColorSpan;
import mmc.lib.*;

public class EditTextMMC extends EditTextSelect {
    
	public int e = 0;
	public int s = 1;
	
	private ArrayList<TextWatcher> mListeners = null;
	private String launges = "";
	private Rect mRect,lineBounds;
    private Paint mPaint,highlightPaint;
	public String i,h;
	private int HIGHLIGHTER_YELLOW = 0x01f3f304;
    private int lineNumber;
	public float size = 0;
	private boolean is = false,draw = true;
	
    public interface onSelectListing{
        public void getSelect(int start, int end, String text);
        public void getTextOne(String t1,String t2);
        public void getBracket(int left, int position, int right, String regex);
        public void getErrors(String error);
    }
    
    public EditTextMMC(Context context) {
        super(context);
    }
    public EditTextMMC(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
		lineBounds = new Rect();
        highlightPaint = new Paint();
        highlightPaint.setColor(HIGHLIGHTER_YELLOW);
		this.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					is = true;
				}else{
					is = false;
				}
			}
		});
    }
    
	@Override
    public void addTextChangedListener(TextWatcher watcher){       
        if (mListeners == null){
            mListeners = new ArrayList<TextWatcher>();
        }
        mListeners.add(watcher);
        super.addTextChangedListener(watcher);
    }
	@Override
    public void removeTextChangedListener(TextWatcher watcher){       
        if (mListeners != null){
            int i = mListeners.indexOf(watcher);
            if (i >= 0){
                mListeners.remove(i);
            }
        }
        super.removeTextChangedListener(watcher);
    }
	public void clearTextChangedListeners(){
        if(mListeners != null){
            for(TextWatcher watcher : mListeners){
                super.removeTextChangedListener(watcher);
            }
            mListeners.clear();
            mListeners = null;
        }
    }
	public void Editor(String patch,String i){
		h=patch;
		size = Float.valueOf(i);
		setTextSize(size);
		launges = "";
		if(patch.endsWith(".js")){launges = Launges.JavaScript;}
		if(patch.endsWith(".json")){launges = Launges.Json;}
		if(patch.endsWith(".hjson")){launges = Launges.Hjson;}
		final String launge = launges;
		clearTextChangedListeners();
	    addTextChangedListener(new TextWatcher() {
				ColorScheme launges = new ColorScheme(
					Pattern.compile(launge),
					Color.RED
				);
				ColorScheme numbers = new ColorScheme(
					Pattern.compile(Launges.Number),
					Color.BLUE
				);
				ColorScheme brackets = new ColorScheme(
					Pattern.compile(Launges.Bracket),
					Color.GREEN
				);
				ColorScheme comments = new ColorScheme(
					Pattern.compile(Launges.Comment),
					Color.GRAY
				);
				ColorScheme colors = new ColorScheme(
					Pattern.compile(Launges.Color),
					Color.RED
				);
                ColorScheme js = new ColorScheme(
                    Pattern.compile(Launges.JS),
                    Color.RED
				);
				final ColorScheme[] schemes = { numbers, brackets, launges, comments, colors, js };
				@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				@Override public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
					final String _charSeq = _param1.toString();
					FileUtil.writeFile(h, _charSeq);
				}
				@Override public void afterTextChanged(Editable s) {
					removeSpans(s, ForegroundColorSpan.class);
					removeSpans(s, UnderlineSpan.class);
					for (ColorScheme scheme : schemes) {
						for(Matcher m = scheme.pattern.matcher(s); m.find();) {
                            if(!String.valueOf(scheme.pattern).equals(Launges.JS)){
							if(!String.valueOf(scheme.pattern).equals(Launges.Color)){
								s.setSpan(new ForegroundColorSpan(scheme.color),m.start(),m.end(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
							}else{
								String color = m.group().toString().replace("\"","").replace("[","").replace("]","");
								s.setSpan(new ForegroundColorSpan(Color.parseColor(color)),m.start(),m.end(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
								UnderlineSpan underline = new UnderlineSpan();
								s.setSpan(underline, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							}
                            }else{
                                s.setSpan(new ForegroundColorSpan(scheme.color),m.start(2),m.end(2),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
						}
					}
				}
				void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
					CharacterStyle[] spans = e.getSpans(0, e.length(), type);
					for (CharacterStyle span : spans) {
						e.removeSpan(span);
					}}
				class ColorScheme {
					final Pattern pattern;
					final int color;
					ColorScheme(Pattern pattern, int color) {
						this.pattern = pattern;
						this.color = color;
					}}});
		setText(FileUtil.readFile(patch));
	}
    @Override
    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Rect r = mRect;
        Paint paint = new Paint();
		Paint paint2 = new Paint();
		paint.setTextSize(14);
		paint.setStyle(Paint.Style.FILL);
		paint2.setStyle(Paint.Style.FILL);
		paint.setColor(getResources().getColor(R.color.text));
		paint2.setColor(0xff898900);
		if (is) {
            lineNumber = getLayout().getLineForOffset(getSelectionStart());
            getLineBounds(lineNumber, lineBounds);
            canvas.drawRect(lineBounds, highlightPaint);
        }
		
        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, r);
			if(draw){
				canvas.drawText(String.valueOf(i+1),2, baseline + 1, paint);
			}
			canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint2);
        }

        super.onDraw(canvas);
    }
	
	public EditTextMMC setDraw(boolean b){
		this.draw = b;
		return this;
	}
	
	public void setSizePlus(int i){
		this.setTextSize(this.getTextSize()+i);
	}
	
	public void setSizeMinus(int i){
		this.setTextSize(this.getTextSize()-i);
	}
	
	public EditTextMMC setSetting(Drawable d){
		setBackgroundDrawable(d);
		return this;
	}
	
	public EditTextMMC setSetting(Drawable d,float size){
		setBackgroundDrawable(d);
		setTextSize(size);
		return this;
	}
	
	public EditTextMMC setColor(String color){
		setTextColor(Color.parseColor(color));
		return this;
	}
	
	public EditTextMMC setColor(int color){
		setTextColor(color);
		return this;
	}
}
