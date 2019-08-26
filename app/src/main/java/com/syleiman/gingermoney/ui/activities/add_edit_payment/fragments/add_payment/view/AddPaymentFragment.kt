package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditPaymentAddBinding
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.StartSelectingTimeCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model.AddPaymentViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account.AccountsKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category.CategoriesKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands.*
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.AmountIsEmptyError
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.CategoryIsEmptyError
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowAmountKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboard
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.fragment_add_edit_payment_add.*

class AddPaymentFragment : FragmentBase<FragmentAddEditPaymentAddBinding, AddPaymentModel, AddPaymentViewModel>() {

    private var accountsKeyboard: AccountsKeyboard? = null
    private var categoriesKeyboard: CategoriesKeyboard? = null
    private var amountKeyboard: AmountKeyboard? = null

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<AddPaymentViewModel> = AddPaymentViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_payment_add

    override fun inject() = App.injections.get<AddPaymentFragmentComponent>().inject(this)

    override fun releaseInjection() {
        App.injections.release<AddPaymentFragmentComponent>()
    }

    override fun linkViewModel(binding: FragmentAddEditPaymentAddBinding, viewModel: AddPaymentViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.dialogCommands.observe({this.viewLifecycleOwner.lifecycle}) {
            processDialogCommand(it)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memoText.setOnFocusChangeListener { _, hasFocus -> if(hasFocus) viewModel.onMemoFieldClick() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onActive()
    }

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveBackViewCommand -> navigation.moveBack(this, true)
            is ShowAccountsKeyboard -> showAccountsKeyboard(command.items)
            is ShowCategoriesKeyboard -> showCategoriesKeyboard(command.items)
            is ShowAmountKeyboard -> showAmountKeyboard(command)
            is HideKeyboard -> hideKeyboards(command.args)
            is MoveToListOfCategoriesCommand -> navigation.moveToListOfCategories(this)
            is ShowErrorCommand -> showError(command.error)
        }
    }

    private fun processDialogCommand(command: ViewCommand) {
        when(command) {
            is StartSelectingDateCommand -> startSelectingDate(command.dateTime)
            is StartSelectingTimeCommand -> startSelectingTime(command.dateTime)
        }
    }

    @Suppress("SpellCheckingInspection")
    private fun startSelectingDate(dateTime: ZonedDateTime) {
        DatePickerDialog.newInstance(
            { _, year, monthOfYear, dayOfMonth ->
                viewModel.onDateSelected(year, monthOfYear + 1, dayOfMonth)
            },
            dateTime.year,
            dateTime.monthValue - 1,
            dateTime.dayOfMonth
        )
        .apply {
            accentColor = resourcesProvider.getColor(R.color.primary)
            show(this@AddPaymentFragment.fragmentManager!!, "Datepickerdialog")
        }
    }

    @Suppress("SpellCheckingInspection")
    private fun startSelectingTime(dateTime: ZonedDateTime) {
        TimePickerDialog.newInstance(
            { _, hourOfDay, minute, _ ->
                viewModel.onTimeSelected(hourOfDay, minute)
            },
            dateTime.hour,
            dateTime.minute,
            resourcesProvider.getBool(R.bool.use24HourTimeFormatInTimeDialog)
        )
        .apply {
            accentColor = resourcesProvider.getColor(R.color.primary)
            show(this@AddPaymentFragment.fragmentManager!!, "Timepickerdialog")
        }
    }

    private fun showAccountsKeyboard(accounts: List<NamedListItem>) {
        if(accountsKeyboard == null) {
            accountsKeyboard = AccountsKeyboard(root, requireContext(), viewModel)
        }
        accountsKeyboard?.show(accounts)
    }

    private fun showCategoriesKeyboard(categories: List<NamedListItem>) {
        if(categoriesKeyboard == null) {
            categoriesKeyboard = CategoriesKeyboard(root, requireContext(), viewModel)
        }
        categoriesKeyboard?.show(categories)
    }

    private fun showAmountKeyboard(command: ShowAmountKeyboard) {
        if(amountKeyboard == null) {
            amountKeyboard = AmountKeyboard(root, requireContext(), command.currencies, command.canEditCurrency, command.canEditSign)
            amountKeyboard?.setOnEditingListener { viewModel.onAmountEdit(it) }
        }
        amountKeyboard?.show(command.value)
    }

    private fun showError(error: DisplayingError) {
        when(error) {
            is CategoryIsEmptyError -> uiUtils.showError(R.string.addEditPaymentEmptyCategoryError)
            is AmountIsEmptyError -> uiUtils.showError(R.string.addEditPaymentEmptyAmountError)
            else -> uiUtils.showError(R.string.commonGeneralError)
        }
    }

    private fun hideKeyboards(keyboards: Array<out Keyboard>) {
        keyboards.forEach { keyboard ->
            when(keyboard) {
                Keyboard.ACCOUNT -> accountsKeyboard?.hide()
                Keyboard.CATEGORY -> categoriesKeyboard?.hide()
                Keyboard.AMOUNT -> amountKeyboard?.hide()
                Keyboard.TEXT -> {
                    uiUtils.setSoftKeyboardVisibility(memoText, false)
                    memoText.clearFocus()
                }
            }
        }
    }
}
