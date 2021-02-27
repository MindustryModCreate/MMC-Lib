package mmc;
import java.util.HashMap;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MMCDialog {
    public Activity activity;
    AlertDialog d;
    public interface Listing{
        void onOk(MMCDialog dialog, int color);
        void onCancel(MMCDialog dialog);
        void onView(View view);
    }
    public MMCDialog(Activity ctx){
        this(ctx,null);
    }
    public MMCDialog(Activity ctx,final Listing lister){
        final View view = LayoutInflater.from(ctx).inflate(R.layout.dialog, null);
        view.setPadding(0,0,0,0);
        d = new AlertDialog.Builder(ctx)
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (lister != null) {
                        lister.onOk(MMCDialog.this, 5);
                        dismiss();
                    }
                }
            })
            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (lister != null) {
                        lister.onCancel(MMCDialog.this);
                        dismiss();
                    }
                }
            }).create();
        if (lister != null) {
            LinearLayout lin = view.findViewById(R.id.lin);
            d.setView(lin, 0, 0, 0, 0);
            lister.onView(lin);
        }
    }
    public void show(){
        d.show();
    }
    
    public void dismiss(){
        d.dismiss();
    }
}
