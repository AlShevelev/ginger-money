package com.shevelev.alphaEmojiPanel.lists

import android.view.ViewGroup
import com.shevelev.alphaEmojiPanel.popup.IconActions

/**
 *
 */
class GridIconsAdapter(private val iconActions: IconActions): IconsAdapterBase<GridIconViewHolder>() {
    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridIconViewHolder =
        GridIconViewHolder(parent.context, parent, iconActions)
}