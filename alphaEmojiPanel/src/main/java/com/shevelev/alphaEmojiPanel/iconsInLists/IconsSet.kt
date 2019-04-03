package com.shevelev.alphaEmojiPanel.iconsInLists

import com.shevelev.alphaEmojiPanel.emojies.Emoji

/**
 * A set of icons
 */
class IconsSet(
    override val icon: Emoji,
    private val icons: List<IconInGrid>
) : GroupIconInGrid {

    /**
     *
     */
    override fun getChildIcons(): List<IconInGrid> = icons
}