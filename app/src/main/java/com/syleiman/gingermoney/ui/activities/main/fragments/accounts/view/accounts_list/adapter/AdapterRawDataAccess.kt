package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter

import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

interface AdapterRawDataAccess<TItem: ListItem> {
    fun getItem(position: Int): TItem
}