package com.shevelev.alphaEmojiPanel.iconsInLists

import com.shevelev.alphaEmojiPanel.emojies.Emoji

/**
 * Set of relative icons (for example a man with various skin colors)
 */
class ComplexIcon(private val icons: List<IconInGrid>): GroupIconInGrid {
    /**
     *
     */
    override val icon: Emoji
        get() = icons[0].icon

    /**
     *
     */
    override fun getChildIcons(): List<IconInGrid> = icons
}