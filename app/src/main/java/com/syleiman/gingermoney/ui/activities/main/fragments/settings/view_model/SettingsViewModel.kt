package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_model

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection.SettingsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectAppProtectionMethodCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectCurrencyDialogCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectStartDayOfWeekCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModelBase<SettingsModelInterface>() {

    val buttonsEnabled: MutableLiveData<Boolean> = MutableLiveData()

    init {
        App.injections.get<SettingsFragmentComponent>().inject(this)

        buttonsEnabled.value = true
    }

    fun onAppProtectionButtonClick() {
        buttonsEnabled.value = false

        launch {
            val allMethods = model.getAppProtectionMethods()
            val method = model.getAppProtectionMethod()

            buttonsEnabled.value = true

            method.error
                ?.let {
                    command.value = ShowErrorCommand(it)
                }

            method.value
                ?.let {
                    dialogCommands.value = StartSelectAppProtectionMethodCommand(allMethods.indexOf(it), allMethods)
                }
        }
    }

    fun onDefaultCurrencyButtonClick() {
        buttonsEnabled.value = false

        launch {
            val defaultCurrency = model.getDefaultCurrency()

            buttonsEnabled.value = true

            defaultCurrency.error
                ?.let {
                    command.value = ShowErrorCommand(it)
                }

            defaultCurrency.value
                ?.let {
                    dialogCommands.value =
                        StartSelectCurrencyDialogCommand(
                            it
                        )
                }
        }
    }

    fun onStartDayOfWeekButtonClick() {
        buttonsEnabled.value = false

        launch {
            val allDays = model.getAllDaysOfWeek()
            val startDayOfWeek = model.getStartDayOfWeek()

            buttonsEnabled.value = true

            startDayOfWeek.error
                ?.let {
                    command.value = ShowErrorCommand(it)
                }

            startDayOfWeek.value
                ?.let {
                    dialogCommands.value = StartSelectStartDayOfWeekCommand(allDays.indexOf(it), allDays)
                }
        }
    }

    fun onAppProtectionSelected(selectedIndex: Int) {
        buttonsEnabled.value = false

        launch {
            val protectionMethod = model.getAppProtectionMethods()[selectedIndex]
            val saveResult = model.saveAppProtectionMethod(protectionMethod)

            buttonsEnabled.value = true

            saveResult?.let {
                command.value = ShowErrorCommand(it)
            }
        }
    }

    fun onDefaultCurrencySelected(selectedCurrency: Currency) {
        buttonsEnabled.value = false

        launch {
            val saveResult = model.saveDefaultCurrency(selectedCurrency)

            buttonsEnabled.value = true

            saveResult?.let {
                command.value = ShowErrorCommand(it)
            }
        }
    }

    fun onStartDayOfWeekSelected(selectedIndex: Int) {
        buttonsEnabled.value = false

        launch {
            val startDay = model.getAllDaysOfWeek()[selectedIndex]

            val saveResult = model.saveStartDayOfWeek(startDay)

            buttonsEnabled.value = true

            saveResult?.let {
                command.value = ShowErrorCommand(it)
            }
        }
    }
}