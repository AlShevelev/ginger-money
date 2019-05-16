package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands.StartSelectAccountGroupCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

/**
 *
 */
class AddAccountViewModel : ViewModelBase<AddAccountModelInterface>() {
    val currency: MutableLiveData<Currency> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()     // Loading indicator

    val group: MutableLiveData<AccountGroup> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<AddEditAccountActivityComponent>().inject(this)

        initView()
    }

    /**
     *
     */
    fun onAccountGroupClick() {
        dialogCommands.value = StartSelectAccountGroupCommand(model.getAllAccountGroups())
    }

    /**
     *
     */
    fun onAccountGroupSelected(selectedIndex: Int) {
        group.value = model.getAllAccountGroups()[selectedIndex]
    }

    /**
     *
     */
    private fun initView() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val defaultCurrency = model.getDefaultCurrency()

            loadingVisibility.value = View.GONE

            defaultCurrency.value?.let { currency.value = it }
            defaultCurrency.error?.let { command.value = ShowErrorCommand(it) }
        }
    }
}