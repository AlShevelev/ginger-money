package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.dto.view_commands.*
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.formatters.MoneySoftCentsFormatter
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardEditingResult
import kotlinx.coroutines.launch

@Suppress("LeakingThis")
abstract class AccountViewModelBase<TM: AddAccountModelInterface> : ViewModelBase<TM>() {
    protected lateinit var amountRaw: Money

    protected abstract val canUpdateCurrency: Boolean


    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()     // Loading indicator

    val group: MutableLiveData<AccountGroup> = MutableLiveData()

    val amount: MutableLiveData<String> = MutableLiveData()     // Must be string because it depends on formatting

    val buttonsEnabled: MutableLiveData<Boolean> = MutableLiveData()

    val name: MutableLiveData<String> = MutableLiveData()

    val nameMaxLen: MutableLiveData<Int> = MutableLiveData()

    val memo: MutableLiveData<String> = MutableLiveData()

    val memoMaxLen: MutableLiveData<Int> = MutableLiveData()

    init {
        inject()

        nameMaxLen.value = model.nameMaxLen
        memoMaxLen.value = model.memoMaxLen
        buttonsEnabled.value = true

        initView()
    }

    abstract fun inject()

    fun onAccountGroupClick() {
        dialogCommands.value = StartSelectAccountGroupCommand(model.getAllAccountGroups())
    }

    fun onAccountGroupSelected(selectedIndex: Int) {
        group.value = model.getAllAccountGroups()[selectedIndex]
    }

    fun onAmountClick() {
        command.value = HideSoftKeyboard()
        command.value = HideEmojiKeyboard()
        command.value = ShowAmountKeyboard(amountRaw, model.getAllCurrencies(), canUpdateCurrency)
    }

    fun onAmountEdit(result: AmountKeyboardEditingResult) {
        amountRaw = result.value

        val formatter = if(result.hasCents) MoneyHardCentsFormatter() else MoneySoftCentsFormatter()
        amount.value = formatter.format(result.value)
    }

    fun onMemoTextFieldFocusChanged(hasFocus: Boolean) {
        if(hasFocus) {
            command.value = HideAmountKeyboard()
            command.value = HideEmojiKeyboard()
        }
    }

    fun onNameTextFieldFocusChanged(hasFocus: Boolean) {
        if(hasFocus) {
            command.value = HideAmountKeyboard()
        }
    }

    fun onEmojiKeyboardOpened() {
        command.value = HideAmountKeyboard()
    }

    fun onSaveButtonClick() {
        command.value = HideSoftKeyboard()
        command.value = HideEmojiKeyboard()
        command.value = HideAmountKeyboard()

        buttonsEnabled.value = false

        launch {
            val saveError = model.save(group.value, name.value, amountRaw, memo.value)
            buttonsEnabled.value = true

            if(saveError == null) {
                command.value = MoveBackViewCommand()   // Close the screen
            } else {
                command.value = ShowErrorCommand(saveError)
            }
        }
    }

    protected abstract fun initView()
}