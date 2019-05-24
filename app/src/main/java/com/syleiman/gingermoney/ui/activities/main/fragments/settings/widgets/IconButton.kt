package com.syleiman.gingermoney.ui.activities.main.fragments.settings.widgets

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.Button
import androidx.annotation.Px
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.extension.inscribe

/**
 * Button with an icon on the left
 */
class IconButton
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.buttonStyle
) : Button(context, attrs, defStyleAttr) {

    private lateinit var icon: Drawable

    private var color: ColorStateList? = null

    @Px
    private var iconSize: Int = 0
    @Px
    private var iconMargin: Int = 0

    init {
        attrs?.let { retrieveAttributes(it, defStyleAttr) }
        drawIcon()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun retrieveAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconButton)

        icon = typedArray.getDrawable(R.styleable.IconButton_iconButton_icon)!!

        color = typedArray.getColorStateList(R.styleable.IconButton_iconButton_iconTint)

        iconSize = typedArray.getDimension(R.styleable.IconButton_iconButton_iconSize, 0f).toInt()
        iconMargin = typedArray.getDimension(R.styleable.IconButton_iconButton_iconMargin, 0f).toInt()

        typedArray.recycle()
    }

    private fun drawIcon() {
        icon.bounds = icon.inscribe(iconSize)
        icon.setTintList(color)

        compoundDrawablePadding = iconMargin
        setCompoundDrawables(icon, null, null, null)
    }
}