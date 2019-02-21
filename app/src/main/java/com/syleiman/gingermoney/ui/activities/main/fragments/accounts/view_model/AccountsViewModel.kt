package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand

/**
 *
 */
class AccountsViewModel : ViewModelBase<AccountsModelInterface>() {

    val stubVisibility: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<MainActivityComponent>().inject(this)

        stubVisibility.value = View.GONE

        fillAccountsList()
    }

    /** */
    private fun fillAccountsList() {
        loadingVisibility.value = View.VISIBLE
        model.getAllAccounts { accounts, error ->
            loadingVisibility.value = View.GONE

            if(accounts != null) {
                if(accounts.isEmpty()) {
                    stubVisibility.value = View.VISIBLE
                }
                else {
                    // do something
                }
            }
            else {
                command.value = ShowErrorCommand(error!!)
            }
        }
    }
}