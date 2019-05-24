package com.syleiman.gingermoney.ui.activities.setup.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.syleiman.gingermoney.R

/**
 * Show current page as a set of dots
 */
class CurrentPage
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Index of a current page (from 0)
    private var currentPageIndex = 0

    private var totalPages = 1

    // Default width and height
    private var defWidth = 0
    private var defHeight = 0

    private val drawingRect = RectF(0f, 0f, 0f, 0f)
    private val drawingPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        attrs?.let { retrieveAttributes(attrs, defStyleAttr) }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun retrieveAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CurrentPage)

        drawingPaint.color = typedArray.getColor(R.styleable.CurrentPage_currentPage_color, 0)
        currentPageIndex = typedArray.getInteger(R.styleable.CurrentPage_currentPage_index, 0)
        totalPages = typedArray.getInteger(R.styleable.CurrentPage_currentPage_totalPages, 0)

        defWidth = typedArray.getDimensionPixelOffset(R.styleable.CurrentPage_currentPage_width, 0)
        defHeight = typedArray.getDimensionPixelOffset(R.styleable.CurrentPage_currentPage_height, 0)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)

        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            width = defWidth
            height = defHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawingRect.right = width.toFloat()
        drawingRect.bottom = height.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        val strokeWidth = drawingRect.height() / 10

        for(i in 0 until totalPages) {
            if(i == currentPageIndex) {
                drawingPaint.style = Paint.Style.FILL
            } else {
                drawingPaint.style = Paint.Style.STROKE
                drawingPaint.strokeWidth = strokeWidth
                drawingPaint.strokeCap
            }

            val radius = drawingRect.height()/2 - strokeWidth/2
            val cy = drawingRect.height()/2
            val cx = (drawingRect.width()/totalPages) * i + cy

            canvas?.drawCircle(cx, cy, radius, drawingPaint)

        }
    }
}