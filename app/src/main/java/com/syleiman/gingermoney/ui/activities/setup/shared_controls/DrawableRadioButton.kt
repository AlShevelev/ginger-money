package com.syleiman.gingermoney.ui.activities.setup.shared_controls

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.RadioButton
import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.extension.inscribe

/**
 * RadioButton with an icon on the right
 */
class DrawableRadioButton
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.radioButtonStyle
) : RadioButton(context, attrs, defStyleAttr) {

    private lateinit var icon: Drawable

    @ColorInt
    private var color: Int = 0

    @Px
    private var iconSize: Int = 0
    @Px
    private var iconMargin: Int = 0

    init {
        attrs?.let { retrieveAttributes(attrs, defStyleAttr) }
        drawIcon()
    }

    /**
     *
     */
    @Suppress("UNUSED_PARAMETER")
    private fun retrieveAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawableRadioButton)

        icon = typedArray.getDrawable(R.styleable.DrawableRadioButton_drawableRadioButton_icon)!!

        color = typedArray.getColor(R.styleable.DrawableRadioButton_drawableRadioButton_iconTint, 0)

        iconSize = typedArray.getDimension(R.styleable.DrawableRadioButton_drawableRadioButton_iconSize, 0f).toInt()
        iconMargin = typedArray.getDimension(R.styleable.DrawableRadioButton_drawableRadioButton_iconMargin, 0f).toInt()

        typedArray.recycle()
    }

    /**
     *
     */
    private fun drawIcon() {
        icon.bounds = icon.inscribe(iconSize)
        icon.setTint(color)

        compoundDrawablePadding = iconMargin
        setCompoundDrawables(null, null, icon, null)
    }
}