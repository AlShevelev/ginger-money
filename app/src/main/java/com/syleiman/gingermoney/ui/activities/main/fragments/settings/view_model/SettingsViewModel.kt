package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_model

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectAppProtectionMethodCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectDefaultCurrencyCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectStartDayOfWeekCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand

/**
 *
 */
class SettingsViewModel : ViewModelBase<SettingsModelInterface>() {

    /**
     *
     */
    val buttonsEnabled: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<MainActivityComponent>().inject(this)

        buttonsEnabled.value = true
    }

    /**
     *
     */
    fun onAppProtectionButtonClick() {
        buttonsEnabled.value = false

        val allMethods = model.getAppProtectionMethods()

        model.getAppProtectionMethod { protectionMethod, error ->
            buttonsEnabled.value = true

            command.value = if(error != null) {
                ShowErrorCommand(error)
            }
            else {
                StartSelectAppProtectionMethodCommand(allMethods.indexOf(protectionMethod), allMethods)
            }
        }
    }

    /**
     *
     */
    fun onDefaultCurrencyButtonClick() {
        buttonsEnabled.value = false

        model.getDefaultCurrency { currency, error ->
            buttonsEnabled.value = true

            command.value = if(error != null) {
                ShowErrorCommand(error)
            }
            else {
                StartSelectDefaultCurrencyCommand(currency!!)
            }
        }
    }

    /**
     *
     */
    fun onStartDayOfWeekButtonClick() {
        buttonsEnabled.value = false

        val allDays = model.getAllDaysOfWeek()

        model.getStartDayOfWeek { dayOfWeek, error ->
            buttonsEnabled.value = true

            command.value = if(error != null) {
                ShowErrorCommand(error)
            }
            else {
                StartSelectStartDayOfWeekCommand(allDays.indexOf(dayOfWeek), allDays)
            }
        }
    }

    /**
     *
     */
    fun onAppProtectionSelected(selectedIndex: Int) {
        buttonsEnabled.value = false

        val protectionMethod = model.getAppProtectionMethods()[selectedIndex]

        model.saveAppProtectionMethod(protectionMethod) { saveResult ->
            buttonsEnabled.value = true

            if(saveResult != null) {
                ShowErrorCommand(saveResult)
            }
        }
    }

    /**
     *
     */
    fun onDefaultCurrencySelected(selectedCurrency: Currency) {
        buttonsEnabled.value = false

        model.saveDefaultCurrency(selectedCurrency) { saveResult ->
            buttonsEnabled.value = true

            if(saveResult != null) {
                ShowErrorCommand(saveResult)
            }
        }
    }

    /**
     *
     */
    fun onStartDayOfWeekSelected(selectedIndex: Int) {
        buttonsEnabled.value = false

        val startDay = model.getAllDaysOfWeek()[selectedIndex]

        model.saveStartDayOfWeek(startDay) { saveResult ->
            buttonsEnabled.value = true

            if(saveResult != null) {
                ShowErrorCommand(saveResult)
            }
        }
    }
}