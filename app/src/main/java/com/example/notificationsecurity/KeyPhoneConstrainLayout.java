package com.example.notificationsecurity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;


public class KeyPhoneConstrainLayout extends ConstraintLayout {


    private OnBackKeyListenerConstraint listener;
    private boolean disabledBackKey;


    public KeyPhoneConstrainLayout(Context context) {
        super(context);
    }

    public KeyPhoneConstrainLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KeyPhoneConstrainLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }


    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
//        if (keyEvent.getKeyCode() != KeyEvent.KEYCODE_BACK) {
//            return super.dispatchKeyEvent(keyEvent);
//        }
//        if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
//            return true;
//        }
        Toast.makeText(getContext(), ""+keyEvent.getKeyCode(), Toast.LENGTH_SHORT).show();
        if (this.listener != null) {
                this.listener.onBackKeyPressed();
//            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_HOME) {
//                this.listener.onHomeKeyPressed();
//            }
        }
        return this.disabledBackKey;
    }


    public void setListener(OnBackKeyListenerConstraint listener) {
        this.listener = listener;
    }


    public interface OnBackKeyListenerConstraint {
        void onBackKeyPressed();

        void onHomeKeyPressed();
    }
}
