package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.date_time.withDate
import com.syleiman.gingermoney.core.global_entities.date_time.withTime
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.StartSelectingTimeCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account.AccountsKeyboardEventsProcessor
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category.CategoriesKeyboardEventsProcessor
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands.*
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class AddPaymentViewModel: ViewModelBase<AddPaymentModel>(), AccountsKeyboardEventsProcessor, CategoriesKeyboardEventsProcessor {

    val createdAt: MutableLiveData<ZonedDateTime> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()     // Loading indicator

    val account: MutableLiveData<String> = MutableLiveData()
    val category: MutableLiveData<String> = MutableLiveData()

    init {
        @Suppress("LeakingThis")
        App.injections.get<AddPaymentFragmentComponent>().inject(this)

        createdAt.value = model.getCreateAt()
    }

    fun onActive() {
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

    override fun onAccountSelect(id: Long) {
        account.value = model.setSelectedAccount(id).name
        command.value = HideAccountsKeyboard()
    }

    override fun onCloseAccountKeyboard() {
        command.value = HideAccountsKeyboard()
    }

    fun onAccountFieldClick() {
        command.value = ShowAccountsKeyboard(model.accounts.map { NamedListItem(it.id!!, it.name) })
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        createdAt.value = createdAt.value!!.withTime(hour, minute)
    }

    fun onCategoryFieldClick() {
        command.value = ShowCategoriesKeyboard(model.categories.map { NamedListItem(it.id!!, it.name) })
    }

    override fun onCategorySelect(id: Long) {
        category.value = model.setSelectedCategory(id).name
        command.value = HideCategoriesKeyboard()
    }

    override fun onCloseCategoryKeyboard() {
        command.value = HideCategoriesKeyboard()
    }

    override fun onEditCategories() {
        command.value = HideCategoriesKeyboard()
        command.value = MoveToListOfCategoriesCommand()
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