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
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.formatters.MoneySoftCentsFormatter
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowAmountKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardEditingResult
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class AddPaymentViewModel: ViewModelBase<AddPaymentModel>(), AccountsKeyboardEventsProcessor, CategoriesKeyboardEventsProcessor {
    val createdAt: MutableLiveData<ZonedDateTime> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()     // Loading indicator

    val account: MutableLiveData<String> = MutableLiveData()
    val category: MutableLiveData<String> = MutableLiveData()

    val amount: MutableLiveData<String> = MutableLiveData()     // Must be string because it depends on formatting

    val memo: MutableLiveData<String> = MutableLiveData()
    val memoMaxLen: MutableLiveData<Int> = MutableLiveData()

    init {
        @Suppress("LeakingThis")
        App.injections.get<AddPaymentFragmentComponent>().inject(this)
        initView()
    }

    fun onActive() {
        reloadCategories()
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

    fun onAmountFieldClick() {
        command.value = ShowAmountKeyboard(model.selectedAmount!!, model.getAllCurrencies(), true, false)
    }

    fun onAmountEdit(result: AmountKeyboardEditingResult) {
        model.selectedAmount = result.value

        val formatter = if(result.hasCents) MoneyHardCentsFormatter() else MoneySoftCentsFormatter()
        amount.value = formatter.format(result.value)
    }

    fun onSaveButtonClick() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val saveResult = model.save(createdAt.value!!, memo.value)

            loadingVisibility.value = View.GONE

            if(saveResult != null) {
                command.value = ShowErrorCommand(saveResult)
            } else {
                command.value = MoveBackViewCommand()
            }
        }
    }

    private fun reloadCategories() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val error = model.loadCategories()

            loadingVisibility.value = View.GONE

            model.selectedCategory?.let { category.value = it.name }

            if(error != null) {
                command.value = ShowErrorCommand(error)
                command.value = MoveBackViewCommand()
            }
        }
    }

    private fun initView() {
        loadingVisibility.value = View.VISIBLE

        createdAt.value = model.getCreateAt()
        memoMaxLen.value = model.memoMaxLen

        launch {
            val defaultCurrency = model.getDefaultCurrency()

            if(defaultCurrency.error != null) {
                loadingVisibility.value = View.GONE
                command.value = ShowErrorCommand(defaultCurrency.error)
            } else {
                defaultCurrency.value?.let {
                    model.selectedAmount = it.toMoney(0)
                    onAmountEdit(AmountKeyboardEditingResult(model.selectedAmount!!, true))
                }

                val loadAccountsError = model.loadAccounts()

                loadingVisibility.value = View.GONE

                if(loadAccountsError != null) {
                    command.value = ShowErrorCommand(loadAccountsError)
                } else {
                    account.value = model.selectedAccount.name
                }
            }
        }
    }
}