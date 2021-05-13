package mmc.dialog;

import android.view.*;
import android.app.*;
import android.content.*;
import mmc.lib.R;
import android.widget.*;
import mmc.widget.*;

public class DialogButton2MMC{
	public interface OnDialog{
		public void load(TextViewMMC ok, TextViewMMC close);
		public void ok(AlertDialog dialog);
		public void close(AlertDialog dialog);
	}
	
	public DialogButton2MMC(Activity ctx,String title,String message,String close,String ok,final OnDialog d){
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		final View customLayout = ctx.getLayoutInflater().inflate(R.layout.dialogbutton2mmc, null);
		TextViewMMC OK = customLayout.findViewById(R.id.ok);
		TextViewMMC CLOSE = customLayout.findViewById(R.id.cancel);
		TextViewMMC TITLE = customLayout.findViewById(R.id.title);
		TextViewMMC MESSAGE = customLayout.findViewById(R.id.message);
		builder.setView(customLayout);
		final AlertDialog dialog = builder.create();
		if(close==null||close==""){
			CLOSE.setText("NULL");
		}else{
			CLOSE.setText(close);
		}
		if(ok==null||ok==""){
			OK.setText("NULL");
		}else{
			OK.setText(ok);
		}
		if(title==null||title==""){
			TITLE.setVisibility(View.GONE);
		}else{
			TITLE.setText(title);
		}
		if(message == null||message==""){
			MESSAGE.setVisibility(View.GONE);
		}else{
			MESSAGE.setText(message);
		}
		d.load(OK,CLOSE);
        OK.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					d.ok(dialog);
				}
			}
		);
		CLOSE.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					d.close(dialog);
				}
			}
		);
		dialog.show();
	}
}
