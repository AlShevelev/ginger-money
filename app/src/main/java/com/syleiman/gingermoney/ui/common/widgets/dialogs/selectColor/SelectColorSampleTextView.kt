package com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.TextView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.dto.enums.Color
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import javax.inject.Inject

class SelectColorSampleTextView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private val drawPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val drawingRect = Rect(0, 0, 0, 0)

    var backgroundColor: Color = Color.WHITE
    set(value) {
        field = value
        invalidate()
    }

    @Inject
    internal lateinit var appResourcesProvider: AppResourcesProvider

    init {
        App.injections.get<UIComponent>().inject(this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawingRect.right = w
        drawingRect.bottom = h
    }

    override fun onDraw(canvas: Canvas?) {
        if(canvas == null) {
            return
        }

        drawPaint.style = Paint.Style.FILL
        drawPaint.color = appResourcesProvider.getColor(backgroundColor)
        canvas.drawRect(drawingRect, drawPaint)

        drawPaint.style = Paint.Style.STROKE
        drawPaint.color = appResourcesProvider.getColor(R.color.gray)
        drawPaint.strokeWidth = appResourcesProvider.getDimension(R.dimen.strokeNormal)
        canvas.drawRect(drawingRect, drawPaint)

        super.onDraw(canvas)
    }
}