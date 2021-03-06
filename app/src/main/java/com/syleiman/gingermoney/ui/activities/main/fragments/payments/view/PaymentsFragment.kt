package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentMainPaymentsBinding
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection.PaymentsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.model.PaymentsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.adapter.PaymentsListAdapter
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.PaymentsViewModel
import com.syleiman.gingermoney.ui.activities.main.headers.payments.PaymentsHeaderLink
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import kotlinx.android.synthetic.main.fragment_main_payments.*
import javax.inject.Inject

/**
 * Payments page
 */
class PaymentsFragment :
    FragmentBase<FragmentMainPaymentsBinding, PaymentsModel, PaymentsViewModel>(),
    PaymentsFragmentHeader {

    private lateinit var paymentsListAdapter: PaymentsListAdapter
    private lateinit var paymentsListLayoutManager: LinearLayoutManager

    @Inject
    internal lateinit var headerLink: PaymentsHeaderLink

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<PaymentsViewModel> = PaymentsViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_main_payments

    override fun inject() = App.injections.get<PaymentsFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentMainPaymentsBinding, viewModel: PaymentsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appPaymentButton.setOnClickListener { navigation.moveToAddPayment(this) }
    }

    override fun onStart() {
        super.onStart()
        headerLink.attachFragment(this)
    }

    override fun onStop() {
        super.onStop()
        headerLink.detachFragment()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewActive()
    }

    override fun onPriorMonthButtonClick() = viewModel.onPriorMonthButtonClick()

    override fun onNextMonthButtonClick() = viewModel.onNextMonthButtonClick()

    override fun observeViewModel(viewModel: PaymentsViewModel) {
        viewModel.periodInfo.observe({this.viewLifecycleOwner.lifecycle}) {
            headerLink.header?.setDateToDisplay(it.dateInPeriod, it.isLastPeriod)
        }

        viewModel.paymentsListData.observe({this.viewLifecycleOwner.lifecycle}) {
            updatePaymentsList(it)
        }
    }

    private fun updatePaymentsList(accounts: List<ListItem>) {
        if(!::paymentsListAdapter.isInitialized) {
            paymentsListLayoutManager = LinearLayoutManager(context)

            paymentsListAdapter = PaymentsListAdapter(viewModel)
            paymentsListAdapter.setHasStableIds(true)

            paymentsList.isSaveEnabled = false
            paymentsList.itemAnimator = null
            paymentsList.layoutManager = paymentsListLayoutManager
            paymentsList.adapter = paymentsListAdapter
        }

        paymentsListAdapter.update(accounts)
    }
}
