package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection.PaymentsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.model.PaymentsModel
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentsViewModel : ViewModelBase<PaymentsModel>() {
    val paymentsListVisibility: MutableLiveData<Int> = MutableLiveData()
    val paymentsListData: MutableLiveData<List<ListItem>> = MutableLiveData()

    val stubVisibility: MutableLiveData<Int> = MutableLiveData()
    val stubText: MutableLiveData<String> = MutableLiveData()

    val addPaymentButtonVisibility: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    @Inject
    internal lateinit var appResourcesProvider: AppResourcesProvider

    init {
        App.injections.get<PaymentsFragmentComponent>().inject(this)

        stubVisibility.value = View.VISIBLE
        paymentsListVisibility.value = View.INVISIBLE
        addPaymentButtonVisibility.value = View.INVISIBLE
    }

    fun onViewActive() {
        fillPaymentsList()
    }

    private fun fillPaymentsList() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val payments = model.getPaymentsList()

            loadingVisibility.value = View.GONE

            processCallResult(payments) {
                when {
                    !it.hasAccounts -> {
                        stubText.value = appResourcesProvider.getString(R.string.mainPaymentsNoAccounts)
                        stubVisibility.value = View.VISIBLE
                        addPaymentButtonVisibility.value = View.INVISIBLE
                        paymentsListVisibility.value = View.INVISIBLE
                    }
                    it.payments.isEmpty() -> {
                        stubText.value = appResourcesProvider.getString(R.string.mainPaymentsNoPayments)
                        stubVisibility.value = View.VISIBLE
                        addPaymentButtonVisibility.value = View.VISIBLE
                        paymentsListVisibility.value = View.INVISIBLE
                    }
                    else -> {
                        stubVisibility.value = View.INVISIBLE
                        paymentsListVisibility.value = View.VISIBLE
                        addPaymentButtonVisibility.value = View.VISIBLE

                        // Show payments
                    }
                }
            }
        }
    }
}