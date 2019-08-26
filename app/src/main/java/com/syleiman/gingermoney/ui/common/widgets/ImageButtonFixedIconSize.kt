package com.syleiman.gingermoney.ui.common.widgets

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.annotation.Px
import com.syleiman.gingermoney.R

/**
 * Image button with explicitly set icon size
 */
class ImageButtonFixedIconSize
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.imageButtonStyle
) : ImageButton(context, attrs, defStyleAttr) {

    private lateinit var icon: Drawable

    @Px
    private var iconWidth: Int = 0
    @Px
    private var iconHeight: Int = 0

    private var tint: ColorStateList? = null

    init {
        scaleType = ScaleType.FIT_XY
        attrs?.let { retrieveAttributes(it) }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        val height = bottom - top

        val horizontalPadding = if(width > iconWidth) (width - iconWidth) / 2 else 0
        val verticalPadding = if(height > iconHeight) (height - iconHeight) / 2 else 0

        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)

        tint?.let { icon.setTintList(it) }
        setImageDrawable(icon)

        super.onLayout(changed, left, top, right, bottom)
    }

    fun setEnabledState(isEnabled: Boolean) {
        this.isEnabled = isEnabled
    }

    private fun retrieveAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageButtonFixedIconSize)

        icon = typedArray.getDrawable(R.styleable.ImageButtonFixedIconSize_imageButton_icon)!!

        iconWidth = typedArray.getDimension(R.styleable.ImageButtonFixedIconSize_imageButton_iconWidth, 0f).toInt()
        iconHeight = typedArray.getDimension(R.styleable.ImageButtonFixedIconSize_imageButton_iconHeight, 0f).toInt()

        tint = typedArray.getColorStateList(R.styleable.ImageButtonFixedIconSize_imageButton_tint)

        typedArray.recycle()
    }
}
