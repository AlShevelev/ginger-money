package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.date_time.withDate
import com.syleiman.gingermoney.core.global_entities.date_time.withTime
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.StartSelectingTimeCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard.AccountListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard.AccountsKeyboardEventsProcessor
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.view_commands.HideAccountsKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.view_commands.StartSelectingDateCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.view_commands.ShowAccountsKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class AddPaymentViewModel: ViewModelBase<AddPaymentModel>(), AccountsKeyboardEventsProcessor {
    val createdAt: MutableLiveData<ZonedDateTime> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()     // Loading indicator

    val account: MutableLiveData<String> = MutableLiveData()

    init {
        @Suppress("LeakingThis")
        App.injections.get<AddPaymentFragmentComponent>().inject(this)

        createdAt.value = model.getCreateAt()
        init()
    }

    fun onCreatedAtDateClick() {
        dialogCommands.value =
            StartSelectingDateCommand(
                createdAt.value!!
            )
    }

    fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        createdAt.value = createdAt.value!!.withDate(year, monthOfYear, dayOfMonth)
    }

    fun onCreatedAtTimeClick() {
        dialogCommands.value = StartSelectingTimeCommand(createdAt.value!!)
    }

    fun onAccountClick() {
        command.value = ShowAccountsKeyboard(model.accounts.map { AccountListItem(it.id!!, it.name) })
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        createdAt.value = createdAt.value!!.withTime(hour, minute)
    }

    override fun onAccountItemClick(id: Long) {
        account.value = model.setSelectedAccount(id).name
        command.value = HideAccountsKeyboard()
    }

    override fun onCloseAccountKeyboardClick() {
        command.value = HideAccountsKeyboard()
    }

    private fun init() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val error = model.loadData()

            loadingVisibility.value = View.GONE

            if(error != null) {
                command.value = ShowErrorCommand(error)
                command.value = MoveBackViewCommand()
            }
        }
    }
}