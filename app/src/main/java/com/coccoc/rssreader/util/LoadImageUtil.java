package com.coccoc.rssreader.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class LoadImageUtil {

    private static final String TAG = LoadImageUtil.class.getName();
    private static Context mContext;

    public LoadImageUtil(Context context) {
        mContext = context;
    }

    public static LoadImageUtil get(Context context) {
        return new LoadImageUtil(context);
    }

    private static boolean isOK(String url, ImageView view) {
        return !CommonUtil.isNullOrEmpty(url) && view != null;
    }

    /**
     * init request manager
     * it was call from method return DrawableRequestBuilder
     */
    private static RequestManager getRequestManager() {
        return Glide.with(mContext);
    }

    /**
     * load image into glide
     * it was call from a base load image methods or other
     *
     * @param resId the id of the resource containing the image
     */
    private DrawableTypeRequest<Integer> getTypeRequest(int resId) {
        return getRequestManager().load(resId);
    }

    /**
     * load image into glide
     * it was call from a method that return DrawableRequestBuilder
     *
     * @param url the image url
     */
    private DrawableTypeRequest<String> getTypeRequest(String url) {
        return getRequestManager().load(url);
    }

    /**
     * buid final object to load image into view
     * it was call from base load image method
     *
     * @param url the image url
     */
    private DrawableRequestBuilder<String> getBuilder(String url) {
        return getTypeRequest(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(false)
                .dontAnimate()
                .dontTransform();
    }

    /**
     * buid final object to load image into view
     * it was call from base load image method
     *
     * @param resId the resource id
     */
    private DrawableRequestBuilder<Integer> getBuilder(int resId) {
        return getTypeRequest(resId)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .dontAnimate()
                .dontTransform();
    }

    public void loadNormal(String url, ImageView view) {
        if (!isOK(url, view))
            return;
        try {
            getTypeRequest(url)
                    .into(view);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
