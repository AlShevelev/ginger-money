package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.view_model

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model.BaseCurrencyModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.view_commands.MoveToNextCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

class BaseCurrencyViewModel : ViewModelBase<BaseCurrencyModel>() {

    /**
     * Selected Currency
     */
    val currency: MutableLiveData<Currency> = MutableLiveData()

    val nextButtonEnabled: MutableLiveData<Boolean> = MutableLiveData()

    init {
        App.injections.get<SetupActivityComponent>().inject(this)

        currency.value = model.startCurrencyValue
        nextButtonEnabled.value = true
    }

    fun onNextButtonClick() {
        nextButtonEnabled.value = false

        launch {
            val saveResult = model.saveCurrency(currency.value!!)

            nextButtonEnabled.value = true

            command.value = saveResult?.let { ShowErrorCommand(it) } ?: MoveToNextCommand()
        }
    }
}