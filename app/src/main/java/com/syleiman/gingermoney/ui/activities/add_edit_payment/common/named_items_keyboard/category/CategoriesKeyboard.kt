package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsListAdapterBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent

class CategoriesKeyboard(
    rootView: View,
    context: Context,
    items: List<NamedListItem>,
    keyboardEventsProcessor: CategoriesKeyboardEventsProcessor
): NamedItemsKeyboard<CategoriesKeyboardEventsProcessor>(
    rootView,
    context,
    items,
    keyboardEventsProcessor) {

    override fun inject() = App.injections.get<AddEditPaymentActivityComponent>().inject(this)

    override fun provideLayout(): Int = R.layout.popup_categories_keyboard

    override fun setSize(accountsTotal: Int) {
        val maxRows = 3

        width = WindowManager.LayoutParams.MATCH_PARENT

        var rows = if(accountsTotal == 0) {
            maxRows             // for stub
        } else {
            accountsTotal / columns
        }

        if(accountsTotal % columns != 0) {
            rows++
        }

        if(rows > maxRows) {
            rows = maxRows
        }

        height = (rows * appResourceProvider.getDimension(R.dimen.accountKeyboardItem).toInt()) +
                appResourceProvider.getDimension(R.dimen.headerButtonSize).toInt()
    }

    override fun setHeaderButtonsListeners(keyboardEventsProcessor: CategoriesKeyboardEventsProcessor) {
        contentView.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            keyboardEventsProcessor.onCloseCategoryKeyboard()
        }

        contentView.findViewById<ImageButton>(R.id.editButton).setOnClickListener {
            keyboardEventsProcessor.onEditCategories()
        }
    }

    override fun createAdapter(
        items: List<NamedListItem>,
        keyboardEventsProcessor: CategoriesKeyboardEventsProcessor
    ): NamedItemsListAdapterBase<CategoriesKeyboardEventsProcessor> =
        CategoriesListAdapterBase(keyboardEventsProcessor, items)

    override fun setVisualState(accountsTotal: Int) {
        if(accountsTotal == 0) {
            contentView.findViewById<Group>(R.id.noDataStub).visibility = View.VISIBLE
        } else {
            contentView.findViewById<RecyclerView>(R.id.itemsList).visibility = View.VISIBLE
        }
    }
}