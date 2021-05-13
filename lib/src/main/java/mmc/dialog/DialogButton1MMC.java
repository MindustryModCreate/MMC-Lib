package mmc.dialog;

import android.widget.*;
import android.app.*;
import android.view.*;
import mmc.lib.R;
import mmc.widget.*;

public class DialogButton1MMC{
	public interface OnDialog{
		public void load(TextViewMMC close);
		public void close(AlertDialog dialog);
	}

	public DialogButton1MMC(Activity ctx,String title,String message,String close,final OnDialog d){
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		final View customLayout = ctx.getLayoutInflater().inflate(R.layout.dialogbutton1mmc, null);
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
		d.load(CLOSE);
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
