package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.view_holders

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.recyclerview.widget.RecyclerView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.GroupListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter.AdapterRawDataAccess

/**
 * To draw background of [GroupViewHolder]
 */
class GroupViewHolderItemDecoration(
    private val resourcesProvider: AppResourcesProvider
): RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val backgroundPaint = Paint()
        backgroundPaint.style = Paint.Style.FILL

        val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        strokePaint.color = resourcesProvider.getColor(R.color.gray)
        strokePaint.style = Paint.Style.FILL
        strokePaint.strokeWidth  = resourcesProvider.getDimension(R.dimen.strokeThin)

        processDraw(parent) { listItem, viewBounds ->
            backgroundPaint.color = resourcesProvider.getColor(listItem.colors.backgroundColor)

            c.drawRect(viewBounds, backgroundPaint)

            // Stroke
            c.drawLine(viewBounds.left, viewBounds.top, viewBounds.left, viewBounds.bottom, strokePaint)
            c.drawLine(viewBounds.left, viewBounds.bottom, viewBounds.right, viewBounds.bottom, strokePaint)
            c.drawLine(viewBounds.right, viewBounds.bottom, viewBounds.right, viewBounds.top, strokePaint)
        }
    }

    private fun processDraw(parent: RecyclerView, drawAction: (GroupListItem, RectF) -> Unit) {
        val childCount = parent.childCount

        for(i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val viewType = parent.adapter!!.getItemViewType(position)

            if(viewType == ViewHolderType.GROUP){
                val listItem = (parent.adapter as AdapterRawDataAccess<*>).getItem(position) as GroupListItem

                drawAction(listItem, RectF(view.left.toFloat(), view.top.toFloat(), view.right.toFloat(), view.bottom.toFloat()))
            }
        }
    }
}