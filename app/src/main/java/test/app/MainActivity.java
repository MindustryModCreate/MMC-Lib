package test.app;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.text.Selection;
import android.text.InputType;
import android.database.Cursor;
import android.content.Context;
import android.widget.LinearLayout;
import android.view.accessibility.AccessibilityEvent;
import java.util.regex.Pattern;
import android.graphics.Color;
import android.text.style.CharacterStyle;
import java.util.regex.Matcher;
import android.text.style.BackgroundColorSpan;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import mmc.lib.*;
import mmc.widget.*;
import android.text.style.ImageSpan;
import mmc.dialog.*;
import android.widget.*;
import android.app.*;

public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView version = (TextView)findViewById(R.id.version);
        final TextView b1 = (TextView)findViewById(R.id.b1);
        final TextView b2 = (TextView)findViewById(R.id.b2);
        final EditTextMMC edit = findViewById(R.id.edit);
        this.setTitle("MMC TESTING");
        version.setText(MMC.version+"v");
        edit.setTextColor(0xffff0000);
        // Test
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1){
                edit.setBracket(true);
            }
        });
        
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1){
                edit.setBracket(false);
            }
        });
        
		new DialogButton3MMC(this,"Тестовое приложение",MMC.version+"v","NO","YES","NEUTRAL",
			new DialogButton3MMC.OnDialog(){
				@Override
				public void ok(AlertDialog dialog){
					Toast.makeText(getApplicationContext(),"ОК",Toast.LENGTH_SHORT).show();
				}
				@Override
				public void close(AlertDialog dialog){
					Toast.makeText(getApplicationContext(),"НЕТ",Toast.LENGTH_SHORT).show();
				}
				@Override
				public void neutral(AlertDialog dialog){
					Toast.makeText(getApplicationContext(),"Нейтральная",Toast.LENGTH_SHORT).show();
				}
				@Override
				public void load(TextView ok, TextView close, TextView neutral){
					Toast.makeText(getApplicationContext(),"Диалог загружен...",Toast.LENGTH_SHORT).show();
				}
			}
		);
		
        edit.setSelection(0);
        edit.setText("[..{..(0123456789)...(...(...)(...)...)..}..]");
        
        
        edit.addOnSelecting(new EditTextSelect.onSelectListing(){
            @Override
            public void getSelect(int start,int end, String text){
                
            }
            @Override
            public void getTextOne(String t1, String t2){
                b1.setText(edit.getSelectionStart()+"\n"+t1+"\n"+t2);
            }
            @Override
            public void getBracket(int l, int position, int r,String regex){
                b2.setText(String.valueOf(l+":"+r)+"\n"+regex);
            }
            @Override
            public void getErrors(String error){
                ((TextView)findViewById(R.id.error)).setText(error);
            }
        });
    }
} 
