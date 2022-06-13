package com.shahin.cryptoviewer.ui.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
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