package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands.MoveToEditAccountCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands.StartSelectColorsDialogCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands.StartSelectCurrencyDialogCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

class AccountsViewModel : ViewModelBase<AccountsModel>(), ListItemEventsProcessor {

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

    override fun onAccountClick(accountDbId: Long) {
        command.value = MoveToEditAccountCommand(accountDbId)
    }

    override fun onOnCurrencyMenuItemClick(group: AccountGroup?) {
        launch {
            dialogCommands.value = StartSelectCurrencyDialogCommand(model.getCurrency(group), group)
        }
    }

    override fun onOnColorMenuItemClick(group: AccountGroup) {
        launch {
            val colors = model.getColors(group)
            if (colors.error != null) {
                command.value = ShowErrorCommand(colors.error)
            } else {
                dialogCommands.value = StartSelectColorsDialogCommand(colors.value!!, group)
            }
        }
    }

    fun onOnCurrencySelected(selectedCurrency: Currency, group: AccountGroup?) {
        launch {
            val error = model.updateCurrency(group, selectedCurrency)
            if(error != null) {
                command.value = ShowErrorCommand(error)
            } else {
                fillAccountsList()
            }
        }
    }

    fun onColorsSelected(colors: TextColors, group: AccountGroup) {
        launch {
            val error = model.updateColors(group, colors)
            if(error != null) {
                command.value = ShowErrorCommand(error)
            } else {
                fillAccountsList()
            }
        }
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