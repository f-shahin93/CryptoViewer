package com.shahin.cryptoviewer.ui.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.shahin.cryptoviewer.R

fun marginItemDecoration(
    @DimenRes marginLeft: Int = R.dimen.margin_zero,
    @DimenRes marginRight: Int = R.dimen.margin_zero,
    @DimenRes marginTop: Int = R.dimen.margin_zero,
    @DimenRes marginBottom: Int = R.dimen.margin_zero,
    context: Context,
) = object : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val mLeft = context.resources.getDimensionPixelSize(marginLeft)
        val mRight = context.resources.getDimensionPixelSize(marginRight)
        val mTop = context.resources.getDimensionPixelSize(marginTop)
        val mBottom = context.resources.getDimensionPixelSize(marginBottom)
        with(outRect) {
            left = mLeft
            right = mRight
            top = mTop
            bottom = mBottom
        }
    }

}

fun snackBar(view: View, context: Context, text: String) =
    Snackbar.make(context, view, text, Snackbar.LENGTH_SHORT)

private fun getColorByPercent(context: Context, percent: Float) =
    ContextCompat.getColor(
        context,
        when {
            percent > 0 -> android.R.color.holo_green_light
            percent < 0 -> android.R.color.holo_red_dark
            else -> android.R.color.black
        }
    )

@BindingAdapter("textColorPercent")
fun setTextColorByPercent(textView: TextView, percent: String?) {
    if (percent.isNullOrEmpty()) {
        return
    } else {
        textView.setTextColor(getColorByPercent(textView.context, percent.toFloat()))
    }
}

@BindingAdapter("loadImg")
fun loadImg(imageView: ImageView, currencyId: Long?) {
    val loadUrl = if (currencyId == null)
        getImageUrl()
    else
        getImageUrl(id = currencyId)

    Glide.with(imageView).load(loadUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop().into(imageView)
}

