package com.example.mykotlin.utils;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.mykotlin.R;


/**
 * Created by SUN on 2018/3/26.
 */

public class GlideOptionsSetting {
    public static RequestOptions getDefaultOptions(){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.picture_loading)
                .error(R.drawable.ic_no_picture)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }

    public static RequestOptions getCircleDefaultOptions(){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.picture_loading)
                .error(R.drawable.ic_no_picture)
                .priority(Priority.HIGH)
                .bitmapTransform(new CircleCrop())  //圆形
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }
}
