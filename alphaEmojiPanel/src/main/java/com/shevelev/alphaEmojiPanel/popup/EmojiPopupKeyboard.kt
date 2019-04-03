package com.shevelev.alphaEmojiPanel.popup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Size
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shevelev.alphaEmojiPanel.*
import com.shevelev.alphaEmojiPanel.dto.TouchPoint
import com.shevelev.alphaEmojiPanel.emojies.Emoji
import com.shevelev.alphaEmojiPanel.iconsInLists.*
import com.shevelev.alphaEmojiPanel.lists.GridIconsAdapter
import com.shevelev.alphaEmojiPanel.lists.recent.RecentIconsAdapter
import com.shevelev.alphaEmojiPanel.lists.recent.RecentIconsCollection
import com.shevelev.alphaEmojiPanel.utils.ScreenOrientation
import com.shevelev.alphaEmojiPanel.utils.UIHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *
 */
class EmojiPopupKeyboard (
    private val rootView: View,
    private val context: Context
) : PopupWindow(context),
    IconActions,
    CoroutineScope {

    private var _onEmojiClickedListener: ((Emoji) -> Unit)? = null
    private var _onKeyClickedListener: ((Int) -> Unit)? = null  // param is a key code
    private var _onSoftKeyboardCloseListener: (() -> Unit)? = null

    private var pendingOpen: Boolean = false
    private var isOpened: Boolean = false

    private var keyBoardHeightPortrait = -1
    private var keyBoardHeightLandscape = -1

    private val generalIconsAdapter: GridIconsAdapter
    private val generalList: RecyclerView

    private val recentIconsAdapter: RecentIconsAdapter
    private val recentList: RecyclerView
    private val recentIconsCollection: RecentIconsCollection

    private var complexIconPopup: EmojiPopupComplexIcon? = null

    private val scopeJob: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = scopeJob + Dispatchers.Main

    /**
     *
     */
    init {
        // Create view
        val customView = createView()
        contentView = customView
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        setSize(Size(WindowManager.LayoutParams.MATCH_PARENT, 255))
        setBackgroundDrawable(null)

        // Setting up of general icons list
        generalIconsAdapter = GridIconsAdapter(this)
        generalIconsAdapter.updateData(RootIcons().getChildIcons())

        generalList = customView.findViewById(R.id.generalList)
        generalList.adapter = generalIconsAdapter
        generalList.layoutManager = GridLayoutManager(context, getColumnsQuantity())

        // Setting up of recent icons list
        recentIconsAdapter = RecentIconsAdapter(this)

        recentIconsCollection = RecentIconsCollection()

        recentList = customView.findViewById(R.id.recentList)
        recentList.adapter = recentIconsAdapter
        recentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Set buttons clicks
        customView.findViewById<ImageButton>(R.id.homeButton)
            .let {
                it.setOnClickListener {
                    closeComplexIconPopup()
                    generalIconsAdapter.updateData(RootIcons().getChildIcons())
                }
            }

        customView.findViewById<ImageButton>(R.id.backButton)
            .let {
                it.setOnClickListener {
                    _onKeyClickedListener?.invoke(KeyEvent.KEYCODE_DEL)
                }
            }

        customView.findViewById<Button>(R.id.spaceButton)
            .let {
                it.setOnClickListener {
                    _onKeyClickedListener?.invoke(KeyEvent.KEYCODE_SPACE)
                }
            }
    }

    /**
     *
     */
    @SuppressLint("InflateParams")
    private fun createView(): View =
        (context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.popup_emoji, null, false)

    /**
     * Set the listener for the event of keyboard closing.
     */
    fun setOnSoftKeyboardCloseListener(listener: () -> Unit) {
        _onSoftKeyboardCloseListener = listener
    }

    /**
     * Set the listener for the event when any of the emoji icon is clicked
     */
    fun setOnEmojiClickedListener(listener: (Emoji) -> Unit) {
        _onEmojiClickedListener = listener
    }

    /**
     * Set the listener for the extra keys (backspace & space)
     * [listener] only param is a key code
     */
    fun setOnKeyClickedListener(listener: (Int) -> Unit) {
        _onKeyClickedListener = listener
    }

    /**
     * Use this function to show the emoji popup.
     * NOTE: Since, the soft keyboard sizes are variable on different android devices, the
     * library needs you to open the soft keyboard at least once before calling this function.
     * If that is not possible see showAtBottomPending() function.
     *
     */
    fun showAtBottom() {
        launch {
            recentIconsCollection.load(context)
            recentIconsAdapter.updateData(recentIconsCollection.icons)

            UIHelper.setSoftKeyboardVisibility(context, rootView, false)

            showAtLocation(rootView, Gravity.BOTTOM, 0, 0)
        }
    }

    /**
     * Use this function when the soft keyboard has not been opened yet. This
     * will show the emoji popup after the keyboard is up next time.
     * Generally, you will be calling InputMethodManager.showSoftInput function after
     * calling this function.
     */
    fun showAtBottomPending() =
        if (isKeyBoardOpen()) {
            showAtBottom()
        } else {
            pendingOpen = true
        }

    /**
     * @return Returns true if the soft keyboard is open, false otherwise.
     */
    fun isKeyBoardOpen(): Boolean = isOpened

    /**
     *
     */
    fun isSizeCalculated(): Boolean =
        if (UIHelper.getScreenOrientation(context) === ScreenOrientation.PORTRAIT) {
            keyBoardHeightPortrait != -1
        } else {
            keyBoardHeightLandscape != -1
        }

    /**
     * Dismiss the popup
     */
    override fun dismiss() {
        super.dismiss()

        closeComplexIconPopup()
    }

    /**
     * Call this function to resize the emoji popup according to your soft keyboard size
     */
    fun calculateSize() {
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val screenSize = UIHelper.getScreenSize(context)

            // Portrait
            if (UIHelper.getScreenOrientation(context) === ScreenOrientation.PORTRAIT) {
                if (keyBoardHeightPortrait == -1) {
                    calculatePortraitSizeAndShow(screenSize)
                } else {
                    showWithSomeHeight(keyBoardHeightPortrait)
                }
            } else {
                // Landscape
                if (keyBoardHeightLandscape == -1) {
                    keyBoardHeightLandscape = screenSize.height / 2
                }
                showWithSomeHeight(keyBoardHeightLandscape)
            }
        }
    }

    /**
     *
     */
    override fun onIconClick(icon: IconInGrid, touchPoint: TouchPoint) {
        closeComplexIconPopup()

        when(icon) {
            is IconsSet -> generalIconsAdapter.updateData(icon.getChildIcons())

            is ComplexIcon -> {
                complexIconPopup = EmojiPopupComplexIcon(rootView, context, this, icon, touchPoint)
            }

            is SimpleIcon -> {
                _onEmojiClickedListener?.invoke(icon.icon)

                if(recentIconsCollection.add(icon)) {
                    recentIconsAdapter.updateData(recentIconsCollection.icons)
                    recentList.scrollToPosition(0)

                    launch {
                        recentIconsCollection.save(context)
                    }
                }
            }
        }
    }

    /**
     *
     */
    private fun calculatePortraitSizeAndShow(screenSize: Size) {
        val displayFrame = Rect()
        rootView.getWindowVisibleDisplayFrame(displayFrame)

        var heightDifference = screenSize.height - (displayFrame.bottom - displayFrame.top)

        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            heightDifference -= context.resources.getDimensionPixelSize(resourceId)
        }

        // Keyboard is opened
        if (heightDifference > 100) {
            keyBoardHeightPortrait = heightDifference
            showWithSomeHeight(keyBoardHeightPortrait)
        } else {
            // Keyboard is closed
            isOpened = false
            _onSoftKeyboardCloseListener?.invoke()
        }
    }

    /**
     *
     */
    private fun showWithSomeHeight(height: Int) {
        setSize(Size(WindowManager.LayoutParams.MATCH_PARENT, height))

        isOpened = true

        if (pendingOpen) {
            showAtBottom()
            pendingOpen = false
        }
    }


    /**
     * Manually sets the popup window size
     */
    private fun setSize(size: Size) {
        width = size.width
        height = size.height
    }

    /**
     *
     */
    private fun getColumnsQuantity() = if(UIHelper.getScreenOrientation(context) == ScreenOrientation.PORTRAIT) 8 else 13

    /**
     *
     */
    private fun closeComplexIconPopup() {
        complexIconPopup?.dismiss()
        complexIconPopup = null
    }
}