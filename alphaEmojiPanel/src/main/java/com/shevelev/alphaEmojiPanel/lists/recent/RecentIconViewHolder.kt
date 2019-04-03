package com.shevelev.alphaEmojiPanel.lists.recent

import android.content.Context
import android.view.ViewGroup
import com.shevelev.alphaEmojiPanel.R
import com.shevelev.alphaEmojiPanel.iconsInLists.IconInGrid
import com.shevelev.alphaEmojiPanel.lists.IconViewHolderBase
import com.shevelev.alphaEmojiPanel.popup.IconActions
import kotlinx.android.synthetic.main.popup_emoji_recent_item.view.*

/**
 * Holder for one icon in a recent icons list
 */
class RecentIconViewHolder(
    context: Context,
    parentView: ViewGroup,
    iconActions: IconActions
) : IconViewHolderBase(context, parentView, R.layout.popup_emoji_recent_item, iconActions) {

    /**
     * UI elements must be initialized here
     */
    override fun init(item: IconInGrid) {
        super.init(item)
        itemView.emojiIcon.text = item.icon.toEmoji()
    }
}