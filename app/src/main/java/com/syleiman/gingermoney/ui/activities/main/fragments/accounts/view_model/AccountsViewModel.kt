package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

class AccountsViewModel : ViewModelBase<AccountsModelInterface>(), ListItemEventsProcessor {

    val stubVisibility: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val accountsListData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val accountsListVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        App.injections.get<AccountsFragmentComponent>().inject(this)

        stubVisibility.value = View.VISIBLE
        accountsListVisibility.value = View.INVISIBLE
    }

    fun onViewActive() {
        fillAccountsList()
    }

    private fun fillAccountsList() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val accounts = model.getListItems()

            loadingVisibility.value = View.GONE

            accounts.value
                ?.let {
                    accountsListData.value = it

                    stubVisibility.value = if(it.isEmpty()) View.VISIBLE else View.INVISIBLE
                    accountsListVisibility.value = if(it.isEmpty()) View.INVISIBLE else View.VISIBLE
                }

            accounts.error
                ?.also {
                    command.value = ShowErrorCommand(it)
                }
        }
    }
}