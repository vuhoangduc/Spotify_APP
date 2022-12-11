package com.example.duan1_spotify_clone;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LoadingImg {
    private Activity activity;
    private Dialog dialog;

    public LoadingImg(Activity activity) {
        this.activity = activity;
        dialog = new Dialog(activity);
    }
    public void startDialog(){
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        ImageView imageView = dialog.findViewById(R.id.img_loading);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(activity,R.animator.loading);
        animatorSet.setTarget(imageView);
        animatorSet.start();
    }
    public void dismissDialog(){
          dialog.dismiss();
    }
}
