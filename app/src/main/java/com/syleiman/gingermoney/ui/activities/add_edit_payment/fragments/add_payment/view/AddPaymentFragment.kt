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
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_commands.StartSelectingDateCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model.AddPaymentViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog


class AddPaymentFragment :
    FragmentBase<FragmentAddEditPaymentAddBinding, AddPaymentModel, AddPaymentViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<AddPaymentViewModel> = AddPaymentViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_payment_add

    override fun inject() = App.injections.get<AddPaymentFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentAddEditPaymentAddBinding, viewModel: AddPaymentViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        viewModel.accountsListData.observe({this.viewLifecycleOwner.lifecycle}) {
//            updateAccountsList(it)
//        }

        viewModel.dialogCommands.observe({this.viewLifecycleOwner.lifecycle}) {
            processDialogCommand(it)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.onViewActive()
//    }

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
}
