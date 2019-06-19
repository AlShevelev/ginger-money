package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.viewHolders

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.GroupListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_main_accounts_group_list_item.view.*
import javax.inject.Inject

class GroupViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, ListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_main_accounts_group_list_item, parentView, false)
) {

    private var eventsProcessor: ListItemEventsProcessor? = null

    private var popupMenu: PopupMenu? = null


    @Inject
    internal lateinit var resProvider: AppResourcesProviderInterface

    init {
        App.injections.get<AccountsFragmentComponent>().inject(this)
    }

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: ListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        this.eventsProcessor = listItemEventsProcessor

        (listItem as GroupListItem)
            .let {
                itemView.name.text = resProvider.getAccountGroupString(it.accountGroup)
                itemView.name.setTextColor(resProvider.getColor(it.colors.foregroundColor))

                itemView.amount.text = MoneyHardCentsFormatter().format(it.amount)
                itemView.amount.setTextColor(resProvider.getColor(it.colors.foregroundColor))
            }

        itemView.menuButton.setOnClickListener { createMenu(it, listItem) }
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null

        itemView.menuButton.setOnClickListener(null)
        popupMenu?.dismiss()
    }

    private fun createMenu(view: View, listItem: GroupListItem) {
        popupMenu = PopupMenu(view.context, view)
            .apply {
                val inflater: MenuInflater = this.menuInflater
                inflater.inflate(R.menu.accounts_list_group, this.menu)

                setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.selectCurrency -> eventsProcessor?.onOnCurrencyMenuItemClick(listItem.accountGroup)
                        R.id.selectColors -> eventsProcessor?.onOnColorMenuItemClick(listItem.accountGroup)                    }

                    true
                }

                this.show()
            }
    }
}