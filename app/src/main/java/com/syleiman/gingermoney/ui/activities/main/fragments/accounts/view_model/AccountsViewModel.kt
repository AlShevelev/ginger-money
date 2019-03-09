package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

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

        launch {
            val accounts = model.getAllAccounts()

            loadingVisibility.value = View.GONE

            accounts.value
                ?.also {
                    if(it.isEmpty()) {
                        stubVisibility.value = View.VISIBLE
                    }
                    else {
                        // do something
                    }
                }

            accounts.error
                ?.also {
                    command.value = ShowErrorCommand(it)
                }
        }
    }
}