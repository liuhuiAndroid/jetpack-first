package com.jetpack.first.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view.context)
            .load(imageUrl)
            // 动画渐变加载
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}