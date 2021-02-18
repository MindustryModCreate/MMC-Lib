package test.app;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import m.m.c.MMC;
import m.m.c.MMCUtil;
import android.widget.TextView;

public class MainActivity extends Activity { 
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView version = (TextView)findViewById(R.id.version);
        
        final TextView b1 = (TextView)findViewById(R.id.b1);
        final TextView b2 = (TextView)findViewById(R.id.b2);
        
        version.setText(MMC.version+"v");
        
        // Test
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1){
                b1.setText("MMCUtil.Colors.toHEX(\"#990000\"): "+String.valueOf(MMCUtil.Colors.toHEX("#990000"))+"\nMMCUtil.Colors.toRGB(0X990000): "+MMCUtil.Colors.toRGB(0X990000)+"\nMMCUtil.Colors.toRGB(-6750208): "+MMCUtil.Colors.toRGB(-6750208));
            }
        });
        
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1){
                b2.setText(String.valueOf(MMCUtil.Colors.toHEX("#990000")+"\n"+MMCUtil.Colors.toRGB(0X990000)));
            }
        });
    }
	
} 
