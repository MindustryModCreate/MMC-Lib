package mmc.widget;
import android.widget.EditText;
import android.text.TextWatcher;
import java.util.ArrayList;
import android.graphics.Rect;
import android.graphics.Paint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.text.style.ForegroundColorSpan;
import android.text.style.BackgroundColorSpan;
import android.text.Editable;
import android.graphics.Color;
import android.text.style.CharacterStyle;
import java.util.regex.Pattern;
import android.graphics.Canvas;
import android.text.Spanned;
import mmc.lib.*;
import mmc.span.*;
import android.text.style.ImageSpan;
import android.text.Spannable;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff;

public class EditTextSelect extends EditText {
    private boolean
    bracket_visible = true;
    
    public int color_text = 0xff000000,color_background = 0xff000000;
    
    private String
    t1 = "",
    t2 = "",
    text = "",
    error = "";
    
    private ArrayList<onSelectListing> mSelecting = null;

    @Override
    public void setTextColor(int color){
        this.color_text = color;
        super.setTextColor(color);
    }
    
    public void setBracket(boolean b){
        bracket_visible = b;
    }
    
    public interface onSelectListing{
        public void getSelect(int start, int end, String text);
        public void getTextOne(String t1,String t2);
        public void getBracket(int left, int position, int right, String regex);
        public void getErrors(String error);
    }

    public EditTextSelect(Context context) {
        super(context);
    }
    public EditTextSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public void removeSelecting(onSelectListing select){
        if (mSelecting != null){
            int i = mSelecting.indexOf(select);
            if (i >= 0){
                mSelecting.remove(i);
            }
        }
    }
    
    public void clearSelecting(){
        if(mSelecting != null){
            for(onSelectListing select : mSelecting){
                removeSelecting(select);
            }
            mSelecting.clear();
            mSelecting = null;
        }
    }
    
    public void addOnSelecting(final onSelectListing onSelecting){
        if (mSelecting == null){
            mSelecting = new ArrayList<onSelectListing>();
        }
        mSelecting.add(onSelecting);
        setAccessibilityDelegate(new View.AccessibilityDelegate(){
                @Override
                public void sendAccessibilityEvent(View host, int eventType) {
                    super.sendAccessibilityEvent(host, eventType);
                    if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED){
                        text = getText().toString();
                        if(text.length()>getSelectionStart()-1&&getSelectionStart()>0){
                            t1 = Character.toString(text.charAt(getSelectionStart()-1));
                        }else{
                            t1 = " ";
                        }
                        if(text.length()>getSelectionStart()&&getSelectionStart()>-1){
                            t2 = Character.toString(text.charAt(getSelectionStart()));
                        }else{
                            t2 = " ";
                        }
                        onSelecting.getSelect(getSelectionStart(),getSelectionEnd(),text);
                        onSelecting.getTextOne(t1,t2);
                        String bracket = "";
                        int left = -1;
                        int right = -1;

                        try{

                            if(t1.matches(Launges.Bracket)){
                                right = checkBracket(t1,-1);
                            }else if(t2.matches(Launges.Bracket)){
                                right = checkBracket(t2,0);
                            }else{
                                right = -1;
                            }

                            if(t1.matches(Launges.Bracket)){
                                left = checkBracket(t1,0);
                            }else if(t2.matches(Launges.Bracket)){
                                left = checkBracket(t2,-1);
                            }else{
                                left = -1;
                            }

                            String text = getText().toString();
                            Editable s = getText();

                            removeSpans(s, CursorSpanMMC.class);
                            removeSpans(s, BackgroundSpanMMC.class);
                            if(bracket_visible){
                                int pos = getSelectionStart();
                                if(getSelectionStart()==text.length()){
                                    if(right!=-1&&Character.toString(s.toString().charAt(getSelectionStart()-1)).matches(Launges.Bracket2)){ // ) < на конце
                                        s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()-right,getSelectionStart()-right+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()-1,getSelectionStart(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        pos--;
                                        bracket = getBracket(Character.toString(text.charAt(pos)));
                                    }
                                }else if(right!=-1&&Character.toString(s.toString().charAt(getSelectionStart()-1)).matches(Launges.Bracket2)){ // )<
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()-right,getSelectionStart()-right+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()-1,getSelectionStart(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    pos--;
                                    bracket = getBracket(Character.toString(text.charAt(pos)));
                                }else if(right!=-1&&Character.toString(s.toString().charAt(getSelectionStart())).matches(Launges.Bracket2)){ // >)
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()-right,getSelectionStart()-right+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart(),getSelectionStart()+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    bracket = getBracket(Character.toString(text.charAt(pos)));
                                }else if(getSelectionStart()!=0&&left!=-1&&Character.toString(s.toString().charAt(getSelectionStart()-1)).matches(Launges.Bracket1)){ // (<
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()+left,getSelectionStart()+left+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()-1,getSelectionStart(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    pos--;
                                    bracket = getBracket(Character.toString(text.charAt(pos)));
                                }else if(left!=-1&&Character.toString(s.toString().charAt(getSelectionStart())).matches(Launges.Bracket1)){ // >(
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart()+left,getSelectionStart()+left+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(color_text,color_background,getTextSize()),getSelectionStart(),getSelectionStart()+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    bracket = getBracket(Character.toString(text.charAt(pos)));
                                }
                            }
                        }catch(Exception e){
                            error=(e.toString());
                        }
                        onSelecting.getBracket(left, getSelectionStart() ,right, bracket);
                        onSelecting.getErrors(error);
                    }
                }
            }
        );
    }
    private int checkBracket(String i,int level){
        switch(i){
            case "(":
                return Launges.findClosingBracket(text.substring(getSelectionStart(),text.length()),"()",level);
            case "[":
                return Launges.findClosingBracket(text.substring(getSelectionStart(),text.length()),"[]",level);
            case "{":
                return Launges.findClosingBracket(text.substring(getSelectionStart(),text.length()),"{}",level);
            case ")":
                return Launges.findOpenBracket(text.substring(0,getSelectionStart()),"()",level);
            case "]":
                return Launges.findOpenBracket(text.substring(0,getSelectionStart()),"[]",level);
            case "}":
                return Launges.findOpenBracket(text.substring(0,getSelectionStart()),"{}",level);
            default:
                return -1;
        }
    }
    private String getBracket(String i){
        switch(i){
            case "(":
                return "()";
            case "[":
                return "[]";
            case "{":
                return "{}";
            case ")":
                return "()";
            case "]":
                return "[]";
            case "}":
                return "{}";
            default:
                return null;
        }
    }
    
    private void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
        CharacterStyle[] spans = e.getSpans(0, e.length(), type);
        for (CharacterStyle span : spans) {
            e.removeSpan(span);
        }
    }
    private class ColorScheme {
        final Pattern pattern;
        final int color;
        ColorScheme(Pattern pattern, int color) {
            this.pattern = pattern;
            this.color = color;
        }
    }
}

/* Example select

        EditTextSelect edit = ...;
        edit.addSelect(new EditTextSelect.onSelectListing(){
            @Override
            public void getSelect(int start,int end, String text){
                
            }
            @Override
            public void getTextOne(String t1, String t2){
                
            }
            @Override
            public void getBracket(int l, int position, int r,String regex){
                
            }
            @Override
            public void getErrors(String error){
                
            }
        });

*/
