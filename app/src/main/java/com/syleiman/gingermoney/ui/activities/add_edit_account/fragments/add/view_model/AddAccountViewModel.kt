package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands.ShowAmountKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands.StartSelectAccountGroupCommand
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.formatters.MoneySoftCentsFormatter
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardEditingResult
import kotlinx.coroutines.launch

class AddAccountViewModel : ViewModelBase<AddAccountModelInterface>() {
    private lateinit var amountRaw: Money

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()     // Loading indicator

    val group: MutableLiveData<AccountGroup> = MutableLiveData()

    val amount: MutableLiveData<String> = MutableLiveData()     // Must be string because it depends of formatting

    init {
        App.injections.get<AddEditAccountActivityComponent>().inject(this)

        initView()
    }

    fun onAccountGroupClick() {
        dialogCommands.value = StartSelectAccountGroupCommand(model.getAllAccountGroups())
    }

    fun onAccountGroupSelected(selectedIndex: Int) {
        group.value = model.getAllAccountGroups()[selectedIndex]
    }

    fun onAmountClick() {
        command.value = ShowAmountKeyboard(amountRaw, model.getAllCurrencies())
    }

    fun onAmountEdit(result: AmountKeyboardEditingResult) {
        amountRaw = result.value

        val formatter = if(result.hasCents) MoneyHardCentsFormatter() else MoneySoftCentsFormatter()
        amount.value = formatter.format(result.value)
    }

    private fun initView() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val defaultCurrency = model.getDefaultCurrency()

            loadingVisibility.value = View.GONE

            defaultCurrency.value?.let {
                onAmountEdit(AmountKeyboardEditingResult(it.toMoney(0), true))
            }

            defaultCurrency.error?.let { command.value = ShowErrorCommand(it) }
        }
    }
}