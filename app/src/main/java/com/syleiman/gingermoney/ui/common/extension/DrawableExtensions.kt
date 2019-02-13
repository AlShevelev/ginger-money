package com.syleiman.gingermoney.ui.common.extension

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable

/**
 * Inscribes VectorDrawable into some size
 */
fun Drawable.inscribe(targetSize: Int): Rect {
    if(this !is VectorDrawable) {
        throw UnsupportedOperationException("Only VectorDrawable is supported")
    }

    val iconWidth = this.intrinsicWidth
    val iconHeight = this.intrinsicHeight

    // Square icon
    if(iconWidth == iconHeight) {
        return Rect(0, 0, targetSize, targetSize)
    }

    // Vertical-oriented icon
    if(iconWidth < iconHeight) {
        return Rect(0, 0, (targetSize * (iconWidth.toFloat()/iconHeight)).toInt(), targetSize)
    }

    // Horizontal-oriented icon
    val rectHeight = (targetSize * (iconHeight.toFloat()/iconWidth)).toInt()
    val top = (targetSize - rectHeight)/2
    return Rect(0, top, targetSize, top + rectHeight)
}
