package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.view_model

import android.view.View
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.AccountViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection.EditAccountFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model.EditAccountModel
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardEditingResult
import kotlinx.coroutines.launch

class EditAccountViewModel : AccountViewModelBase<EditAccountModel>() {

    private var _canUpdateCurrency: Boolean = true
    override val canUpdateCurrency: Boolean
        get() = _canUpdateCurrency

    override fun inject() = App.injections.get<EditAccountFragmentComponent>().inject(this)

    override fun initView() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val account = model.getAccount()
            val canUpdateCurrency = model.canUpdateCurrency()

            loadingVisibility.value = View.GONE

            account.value?.let {
                amountRaw = it.amount
                group.value = it.accountGroup
                onAmountEdit(AmountKeyboardEditingResult(it.amount, true))
                name.value = it.name
                memo.value = it.memo
            }

            account.error?.let {
                command.value = ShowErrorCommand(it)
                command.value = MoveBackViewCommand()   // Close the screen
            }

            _canUpdateCurrency = canUpdateCurrency.value ?: false

            canUpdateCurrency.error?.let {
                command.value = ShowErrorCommand(it)
                command.value = MoveBackViewCommand()   // Close the screen
            }
        }
    }
}