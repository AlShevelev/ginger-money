package com.syleiman.gingermoney.ui.common.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderBase<TListItemEventsProcessor, TItem: ListItem>(itemView: View): RecyclerView.ViewHolder(itemView) {
    /**
     * UI elements must be initialized here
     */
    abstract fun init(listItem: TItem, listItemEventsProcessor: TListItemEventsProcessor)

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    open fun release() {}
}