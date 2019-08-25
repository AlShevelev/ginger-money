package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import javax.inject.Inject

/**
 * Universal popup keyboard
 */
@Suppress("LeakingThis")
abstract class NamedItemsKeyboard<T: NamedItemsKeyboardEventsProcessor> (
    private val rootView: View,
    private val context: Context,
    private val keyboardEventsProcessor: T
) : PopupWindow(context) {

    private lateinit var adapter: NamedItemsListAdapterBase<T>

    private var backPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            hide()          // Close the popup
        }
    }

    protected val columns = 3

    @Inject
    internal lateinit var appResourceProvider: AppResourcesProvider

    init{
        inject()

        @SuppressLint("InflateParams")
        contentView = (context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(provideLayout(), null, false)

        setBackgroundDrawable(null)

        animationStyle = R.style.amountKeyboardAnimation

        setHeaderButtonsListeners(keyboardEventsProcessor)
    }

    fun show(items: List<NamedListItem>) {
        setSize(items.size)

        // Init list
        val layoutManager = GridLayoutManager(context, columns)

        adapter = createAdapter(items, keyboardEventsProcessor)
        adapter.setHasStableIds(true)

        val list = contentView.findViewById<RecyclerView>(R.id.itemsList)
        list.isSaveEnabled = false
        list.itemAnimator = null
        list.layoutManager = layoutManager
        list.adapter = adapter

        setVisualState(items.size)

        showAtLocation(rootView, Gravity.BOTTOM, 0, 0)

        // Processes Back button action
        (context as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(backPressedCallback)
    }

    fun hide() {
        dismiss()

        backPressedCallback.remove()
    }

    protected abstract fun inject()

    @LayoutRes
    protected abstract fun provideLayout(): Int

    /**
     * Manually sets the popup window size
     */
    protected abstract fun setSize(accountsTotal: Int)

    protected abstract fun setHeaderButtonsListeners(keyboardEventsProcessor: T)

    protected abstract fun createAdapter(items: List<NamedListItem>, keyboardEventsProcessor: T): NamedItemsListAdapterBase<T>

    protected open fun setVisualState(accountsTotal: Int) {
        // do nothing here
    }
}
