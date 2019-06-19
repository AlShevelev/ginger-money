package com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.dto.enums.Color

/**
 * Grid for color selection
 */
class SelectColorGrid
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val gridItems: Map<Color, SelectColorGridItem>

    private var selectedItem: SelectColorGridItem? = null

    private var onColorChangeListener: ((Color) -> Unit)? = null

    var selectedColor: Color
    get() = selectedItem!!.color
    set(value) {
        selectedItem?.isSelectedColor = false

        selectedItem = gridItems[value]
        selectedItem?.isSelectedColor = true

        onColorChangeListener?.invoke(value)
    }

    init {
        inflate(context, R.layout.widget_select_color_grid, this)

        gridItems = mutableMapOf()

        val rootView = getChildAt(0) as ViewGroup

        for(i in 0 until rootView.childCount) {
            (rootView.getChildAt(i) as? SelectColorGridItem)
                ?.let { item ->
                    gridItems[item.color] = item

                    item.setOnClickListener {
                        selectedColor = item.color
                    }
                }
        }
    }

    fun setOnColorChangeListener(listener: ((Color) -> Unit)?) {
        onColorChangeListener = listener
    }
}