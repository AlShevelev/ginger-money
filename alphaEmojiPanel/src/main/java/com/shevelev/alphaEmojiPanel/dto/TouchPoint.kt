package com.shevelev.alphaEmojiPanel.dto

import android.graphics.Point

/**
 *
 */
data class TouchPoint (
    val absolute: Point,
    val inView: Point
)