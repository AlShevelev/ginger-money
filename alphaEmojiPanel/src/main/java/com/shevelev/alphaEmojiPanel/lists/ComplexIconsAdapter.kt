package com.shevelev.alphaEmojiPanel.lists

import android.view.ViewGroup
import com.shevelev.alphaEmojiPanel.popup.IconActions

/**
 *
 */
class ComplexIconsAdapter(private val iconActions: IconActions): IconsAdapterBase<ComplexIconViewHolder>() {
    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplexIconViewHolder =
        ComplexIconViewHolder(parent.context, parent, iconActions)
}