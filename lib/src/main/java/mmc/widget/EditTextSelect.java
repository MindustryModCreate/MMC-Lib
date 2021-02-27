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
import mmc.*;

public class EditTextSelect extends EditText {
    private boolean
    bracket_visible1 = true,
    bracket_visible2 = true;
    private String
    t1 = "",
    t2 = "",
    text = "",
    error = "";

    public int e = 0;
    public int s = 1;
    public float size = 0;
    
    
    public void setBracket(boolean b1,boolean b2){
        bracket_visible1 = b1;
        bracket_visible2 = b2;
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

    public void addSelect(final onSelectListing on){
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
                        on.getSelect(getSelectionStart(),getSelectionEnd(),text);
                        on.getTextOne(t1,t2);
                        String[]bracket = {"()","{}","[]"};
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
                            if(bracket_visible1){
                                if(text.length()!=0&&getSelectionStart()!=text.length()){
                                    int p=getSelectionStart()-1;
                                    s.setSpan(new CursorSpanMMC(),p,p+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                            if(bracket_visible2){
                                if(getSelectionStart()==text.length()){
                                    if(right!=-1&&Character.toString(s.toString().charAt(getSelectionStart()-1)).matches(Launges.Bracket2)){ // ) < на конце
                                        s.setSpan(new BackgroundSpanMMC(),getSelectionStart()-right,getSelectionStart()-right+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        s.setSpan(new BackgroundSpanMMC(),getSelectionStart()-1,getSelectionStart(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    }
                                }else if(right!=-1&&Character.toString(s.toString().charAt(getSelectionStart()-1)).matches(Launges.Bracket2)){ // )<
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart()-right,getSelectionStart()-right+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart()-1,getSelectionStart(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }else if(right!=-1&&Character.toString(s.toString().charAt(getSelectionStart())).matches(Launges.Bracket2)){ // >)
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart()-right,getSelectionStart()-right+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart(),getSelectionStart()+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }else if(getSelectionStart()!=0&&left!=-1&&Character.toString(s.toString().charAt(getSelectionStart()-1)).matches(Launges.Bracket1)){ // (<
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart()+left,getSelectionStart()+left+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart()-1,getSelectionStart(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }else if(left!=-1&&Character.toString(s.toString().charAt(getSelectionStart())).matches(Launges.Bracket1)){ // >(
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart()+left,getSelectionStart()+left+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    s.setSpan(new BackgroundSpanMMC(),getSelectionStart(),getSelectionStart()+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                        }catch(Exception e){
                            error=(e.toString());
                        }
                        on.getBracket(left, getSelectionStart() ,right, bracket[0]);
                        on.getErrors(error);
                    }
                }
            });
    }
    int checkBracket(String i,int level){
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

    void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
        CharacterStyle[] spans = e.getSpans(0, e.length(), type);
        for (CharacterStyle span : spans) {
            e.removeSpan(span);
        }
    }
    class ColorScheme {
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
