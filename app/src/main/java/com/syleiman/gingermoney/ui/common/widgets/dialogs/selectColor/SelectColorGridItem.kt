package com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import javax.inject.Inject
import com.syleiman.gingermoney.dto.enums.Color as ColorGM

/**
 * One item to select a color
 */
class SelectColorGridItem
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val drawingRect = Rect(0, 0, 0, 0)
    private val drawingPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val markRect = Rect(0, 0, 0, 0)

    @ColorInt
    private var markColor: Int = Color.BLACK
    @ColorInt
    private var fillColor: Int = Color.WHITE
    @ColorInt
    private var strokeColor: Int = Color.BLACK

    private val markResId: Int = R.drawable.ic_check

    val color: ColorGM
        get() = decryptColor(fillColor)

    var isSelectedColor: Boolean = false
    set(value) {
        field = value
        invalidate()
    }

    @Inject
    internal lateinit var appResourcesProvider: AppResourcesProvider

    init {
        App.injections.get<UIComponent>().inject(this)
        attrs?.let { retrieveAttributes(attrs, defStyleAttr) }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun retrieveAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectColorGridItem)

        markColor = typedArray.getColor(R.styleable.SelectColorGridItem_selectColorGridItem_markColor, Color.BLACK)
        fillColor = typedArray.getColor(R.styleable.SelectColorGridItem_selectColorGridItem_fillColor, Color.WHITE)
        strokeColor = typedArray.getColor(R.styleable.SelectColorGridItem_selectColorGridItem_strokeColor, Color.BLACK)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawingRect.right = width
        drawingRect.bottom = height

        val markRectSizeFactor = 0.5f
        markRect.left = (width * markRectSizeFactor / 2).toInt()
        markRect.top = (height * markRectSizeFactor / 2).toInt()
        markRect.right = markRect.left + (width * markRectSizeFactor).toInt()
        markRect.bottom = markRect.top + (height * markRectSizeFactor).toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        if(canvas == null) {
            return
        }

        val cy = drawingRect.height()/2f
        val cx = drawingRect.width()/2f
        val radius = drawingRect.height()/2f

        drawingPaint.style = Paint.Style.FILL
        drawingPaint.color = fillColor
        canvas.drawCircle(cx, cy, radius, drawingPaint)

        val strokeWidth = appResourcesProvider.getDimension(R.dimen.strokeThin)
        drawingPaint.style = Paint.Style.STROKE
        drawingPaint.color = strokeColor
        drawingPaint.strokeWidth = strokeWidth

        canvas.drawCircle(cx, cy, radius-strokeWidth, drawingPaint)

        if(isSelectedColor) {
            val mark = appResourcesProvider.getDrawable(markResId).mutate()
            mark.setTint(markColor)

            mark.bounds = markRect
            mark.draw(canvas)
        }
    }

    /**
     * Calculate internal color by its Int value
     */
    private fun decryptColor(@ColorInt sourceColor: Int): ColorGM {
        for(colorGM in ColorGM.values()) {
            if(appResourcesProvider.getColor(colorGM) == sourceColor) {
                return colorGM
            }
        }
        throw IllegalArgumentException("This color value is not supported: $sourceColor")
    }
}