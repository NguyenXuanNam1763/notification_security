package com.example.notificationsecurity;


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

public class BackKeyRelativeLayout extends RelativeLayout {

    private boolean disabledBackKey = false;

    private OnBackKeyListener listener;

    public BackKeyRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BackKeyRelativeLayout(Context context) {
        super(context);
    }

    public BackKeyRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != KeyEvent.KEYCODE_BACK) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
            return true;
        }
        if (this.listener != null) {
            this.listener.onBackKeyPressed();
        }

        return this.disabledBackKey;
    }

    public void setDisableBackKey(boolean flag) {
        this.disabledBackKey = flag;
    }

    public boolean isDisabledBackKey() {
        return this.disabledBackKey;
    }

    public void setOnBackKeyListener(OnBackKeyListener listener) {
        this.listener = listener;
    }

    public interface OnBackKeyListener {
        void onBackKeyPressed();
    }
}