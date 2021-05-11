package mmc.dialog;

import android.view.*;
import android.app.*;
import android.content.*;
import mmc.lib.R;
import android.widget.*;

public class DialogButton2MMC{
	public interface OnDialog{
		public void load(TextView ok, TextView close);
		public void ok(AlertDialog dialog);
		public void close(AlertDialog dialog);
	}
	
	public DialogButton2MMC(Activity ctx,String title,String message,final OnDialog d){
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		final View customLayout = ctx.getLayoutInflater().inflate(R.layout.dialogbutton2mmc, null);
		TextView OK = customLayout.findViewById(R.id.ok);
		TextView CANCEL = customLayout.findViewById(R.id.cancel);
		TextView TITLE = customLayout.findViewById(R.id.title);
		TextView MESSAGE = customLayout.findViewById(R.id.message);
		builder.setView(customLayout);
		final AlertDialog dialog = builder.create();
		if(title.isEmpty()){
			TITLE.setVisibility(View.GONE);
		}else{
			TITLE.setText(title);
		}
		if(message.isEmpty()){
			MESSAGE.setVisibility(View.GONE);
		}else{
			MESSAGE.setText(message);
		}
		d.load(OK,CANCEL);
        OK.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					d.ok(dialog);
				}
			}
		);
		CANCEL.setOnClickListener(new View.OnClickListener() {
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
