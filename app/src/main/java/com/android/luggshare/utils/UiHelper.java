package com.android.luggshare.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.android.luggshare.R;

public class UiHelper {

    ProgressDialog progressDialog;

    private static UiHelper INSTANCE = null;

    private UiHelper() {
    }

    public static UiHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UiHelper();
        }
        return (INSTANCE);
    }

    public void showLoadingIndicator(Context ctx) {

        progressDialog = new ProgressDialog(ctx, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

    }

    public void hideLoadingIndicator() {
        progressDialog.dismiss();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static void setImageWithGlide(Context ctx, final ImageView imageView, String imgUrl) {

        Glide.with(ctx).load(imgUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("onError", "onError");
                imageView.setBackgroundResource(R.drawable.icon_profile);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }


        }).into(imageView);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
