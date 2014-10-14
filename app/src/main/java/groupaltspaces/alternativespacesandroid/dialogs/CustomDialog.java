package groupaltspaces.alternativespacesandroid.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import groupaltspaces.alternativespacesandroid.R;

/**
 * Created by BrageEkroll on 14.10.2014.
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        setCanceledOnTouchOutside(false);

    }
}
