package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentMainPaymentsBinding
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection.PaymentsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.model.PaymentsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.PaymentsViewModel
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase

/**
 * Payments page
 */
class PaymentsFragment :
    FragmentBase<FragmentMainPaymentsBinding, PaymentsModel, PaymentsViewModel>(),
    PaymentsFragmentHeader {

    override fun provideViewModelType(): Class<PaymentsViewModel> = PaymentsViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_main_payments

    override fun inject() = App.injections.get<PaymentsFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentMainPaymentsBinding, viewModel: PaymentsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewActive()
    }
}
