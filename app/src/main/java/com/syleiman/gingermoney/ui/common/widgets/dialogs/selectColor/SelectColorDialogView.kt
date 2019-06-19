package com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.dto.enums.Color
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import kotlinx.android.synthetic.main.dialog_select_color.view.*
import javax.inject.Inject

/**
 * Dialog view for color selection
 */
class SelectColorDialogView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @Inject
    internal lateinit var appResourcesProvider: AppResourcesProviderInterface

    var textColor: Color
    get() = textColorGrid.selectedColor
    set(value) {
        textColorGrid.selectedColor = value
    }

    var backgroundColor: Color
        get() = backgroundColorGrid.selectedColor
        set(value) {
            backgroundColorGrid.selectedColor = value
        }

    init {
        App.injections.get<UIComponent>().inject(this)

        inflate(context, R.layout.dialog_select_color, this)

        textColorGrid.setOnColorChangeListener {
            sampleTextLabel.setTextColor(appResourcesProvider.getColor(it))
        }

        backgroundColorGrid.setOnColorChangeListener {
            sampleTextLabel.backgroundColor = it
        }
    }

    fun setSampleText(text: String) {
        sampleTextLabel.text = text
    }
}