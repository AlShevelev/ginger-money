package com.syleiman.gingermoney.ui.common.recycler_view

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter.AdapterRawDataAccess

abstract class ListAdapterBase<TListItemEventsProcessor, TItem: ListItem>(
    private val listItemEventsProcessor: TListItemEventsProcessor
) : RecyclerView.Adapter<ViewHolderBase<TListItemEventsProcessor, TItem>>(),
    AdapterRawDataAccess<TItem> {

    protected var items: List<TItem> = listOf()

    open fun update(newItems: List<TItem>) {
        val diffCallback = createDiffAlg(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemId(position: Int): Long = items[getItemPosition(position)].id

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolderBase<TListItemEventsProcessor, TItem>, position: Int) =
        holder.init(items[getItemPosition(position)], listItemEventsProcessor)

    override fun onViewRecycled(holder: ViewHolderBase<TListItemEventsProcessor, TItem>) =  holder.release()

    override fun getItem(position: Int): TItem = items[position]

    protected open fun getItemPosition(sourcePosition: Int): Int = sourcePosition

    protected abstract fun createDiffAlg(oldData: List<TItem>, newData: List<TItem>): DiffAlgBase<TItem>
}