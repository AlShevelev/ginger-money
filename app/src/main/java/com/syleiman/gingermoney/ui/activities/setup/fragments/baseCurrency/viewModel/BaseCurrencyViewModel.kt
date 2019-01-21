package com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.viewModel

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.globalEntities.money.Currency
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.model.BaseCurrencyModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewCommands.MoveToNextCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase

/**
 *
 */
class BaseCurrencyViewModel : ViewModelBase<BaseCurrencyModelInterface>() {

    /**
     * Selected currency
     */
    val currency: MutableLiveData<Currency> = MutableLiveData()

    /**
     * Direct command for view
     */
    val command: MutableLiveData<ViewCommand> = MutableLiveData()

    /**
     *
     */
    val nextButtonEnabled: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<SetupActivityComponent>().inject(this)

        currency.value = model.startCurrencyValue
        nextButtonEnabled.value = true
    }

    /**
     *
     */
    fun onNextButtonClick() {
        nextButtonEnabled.value = false

        model.saveCurrency(currency.value!!) { saveResult ->
            nextButtonEnabled.value = true

            command.value = if(saveResult == null) {
                MoveToNextCommand()     // Ok, move to the next page
            }
            else {
                ShowErrorCommand(saveResult)
            }
        }
    }
}