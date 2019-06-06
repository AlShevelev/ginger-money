package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface AccountsModelInterface: ModelBaseInterface {

    suspend fun getListItems(): ModelCallResult<out List<ListItem>>
}