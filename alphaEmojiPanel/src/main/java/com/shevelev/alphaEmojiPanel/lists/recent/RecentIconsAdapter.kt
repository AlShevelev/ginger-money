package com.shevelev.alphaEmojiPanel.lists.recent

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.shevelev.alphaEmojiPanel.iconsInLists.IconInGrid
import com.shevelev.alphaEmojiPanel.lists.IconsAdapterBase
import com.shevelev.alphaEmojiPanel.popup.IconActions

/**
 *
 */
class RecentIconsAdapter(private val iconActions: IconActions): IconsAdapterBase<RecentIconViewHolder>() {
    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentIconViewHolder =
        RecentIconViewHolder(parent.context, parent, iconActions)

    /**
     *
     */
    override fun updateData(data: List<IconInGrid>) {
        val newItems = data.filter { it.icon.isValid() }

        val diffCallback = EmojiDiffAlg(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}