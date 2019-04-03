package com.shevelev.alphaEmojiPanel.popup

import com.shevelev.alphaEmojiPanel.dto.TouchPoint
import com.shevelev.alphaEmojiPanel.iconsInLists.IconInGrid

/**
 * Actions from icons
 */
interface IconActions {
    /**
     *
     */
    fun onIconClick(icon: IconInGrid, touchPoint: TouchPoint)
}