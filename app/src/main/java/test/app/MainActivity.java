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
                b1.setText("MMCUtil.Colors.toHEX(\"#990000\"): "+String.valueOf(MMC.toColor("#990000"))+"\nMMCUtil.Colors.toRGB(0X990000): "+MMC.toColor(0X990000)+"\nMMCUtil.Colors.toRGB(-6750208): "+MMC.toColor(-6750208));
            }
        });
        
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1){
                MMCUtil.toColor("#009900");
                MMCUtil.toColor(-88399393);
                MMCUtil.toColor(0xff0000);
                b2.setTextColor(MMC.toColor("#ff0000")+MMC.toColor("#0000ff"));
                int i = Integer.MAX_VALUE;
            }
        });
    }
	
} 
