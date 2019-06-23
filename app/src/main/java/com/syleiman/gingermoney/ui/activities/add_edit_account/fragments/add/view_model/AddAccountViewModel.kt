package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model

import android.view.View
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dependency_injection.AddAccountFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.AccountViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardEditingResult
import kotlinx.coroutines.launch

class AddAccountViewModel : AccountViewModelBase<AddAccountModel>() {

    override val canUpdateCurrency: Boolean
        get() = true

    override fun inject() = App.injections.get<AddAccountFragmentComponent>().inject(this)

    override fun initView() {
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