package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model

import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase

abstract class AddPaymentViewModel: ViewModelBase<AddPaymentModel>() {
//    protected lateinit var amountRaw: Money
//    val amount: MutableLiveData<String> = MutableLiveData()     // Must be string because it depends on formatting

    init {
        App.injections.get<AddPaymentFragmentComponent>().inject(this)
    }
}