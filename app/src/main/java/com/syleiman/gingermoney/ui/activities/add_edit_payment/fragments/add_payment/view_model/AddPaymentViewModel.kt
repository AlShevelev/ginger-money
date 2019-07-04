package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.date_time.withDate
import com.syleiman.gingermoney.core.global_entities.date_time.withTime
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.StartSelectingTimeCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_commands.StartSelectingDateCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import org.threeten.bp.ZonedDateTime

class AddPaymentViewModel: ViewModelBase<AddPaymentModel>() {
    val createdAt: MutableLiveData<ZonedDateTime> = MutableLiveData()

    init {
        @Suppress("LeakingThis")
        App.injections.get<AddPaymentFragmentComponent>().inject(this)

        createdAt.value = model.getCreateAt()
    }

    fun onCreatedAtDateClick() {
        dialogCommands.value = StartSelectingDateCommand(createdAt.value!!)
    }

    fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        createdAt.value = createdAt.value!!.withDate(year, monthOfYear, dayOfMonth)
    }

    fun onCreatedAtTimeClick() {
        dialogCommands.value = StartSelectingTimeCommand(createdAt.value!!)
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        createdAt.value = createdAt.value!!.withTime(hour, minute)
    }
}