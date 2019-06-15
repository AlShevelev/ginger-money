package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view

import android.os.Bundle
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.databinding.FragmentMainSettingsBinding
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection.SettingsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectAppProtectionMethodCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectCurrencyDialogCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands.StartSelectStartDayOfWeekCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_model.SettingsViewModel
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.widgets.SelectCurrencyDialog
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import org.threeten.bp.DayOfWeek

/**
 * Fragment for settings
 */
class SettingsFragment : FragmentBase<FragmentMainSettingsBinding, SettingsModelInterface, SettingsViewModel>() {

    override fun provideViewModelType(): Class<SettingsViewModel> = SettingsViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_main_settings

    override fun inject() = App.injections.get<SettingsFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentMainSettingsBinding, viewModel: SettingsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dialogCommands.observe({this.lifecycle}) {
            processDialogCommand(it)
        }
    }

    private fun processDialogCommand(command: ViewCommand) {
        when(command) {
            is StartSelectAppProtectionMethodCommand ->
                startSelectAppProtectionMethod(command.selectedIndex, command.protectionMethods)
            is StartSelectStartDayOfWeekCommand ->
                startSelectStartDayOfWeek(command.selectedIndex, command.daysOfWeek)
            is StartSelectCurrencyDialogCommand ->
                startSelectDefaultCurrency(command.selectedCurrency)
            else -> throw UnsupportedOperationException("This command is not supported: $command")
        }
    }

    private fun startSelectAppProtectionMethod(selectedIndex: Int, protectionMethods: List<AppProtectionMethod>) {
        activeDialog = uiUtils.showOneOptionRadioDialog(
            requireContext(),
            protectionMethods.map { resourcesProvider.getAppProtectionMethodString(it) },
            selectedIndex,
            resourcesProvider.getString(R.string.mainProtectionDialogTitle)
        ) { resultIndex ->
            resultIndex?.also { viewModel.onAppProtectionSelected(it) }
        }
    }

    private fun startSelectStartDayOfWeek(selectedIndex: Int, days: List<DayOfWeek>) {
        activeDialog = uiUtils.showOneOptionRadioDialog(
            requireContext(),
            days.map { resourcesProvider.getDayOfWeekString(it) },
            selectedIndex,
            resourcesProvider.getString(R.string.mainStartDayDialogTitle)
        ) { resultIndex ->
            resultIndex?.also { viewModel.onStartDayOfWeekSelected(it) }
        }
    }

    private fun startSelectDefaultCurrency(selectedCurrency: Currency) {
        activeDialog = SelectCurrencyDialog(
            requireContext(),
            selectedCurrency,
            resourcesProvider.getString(R.string.mainCurrencyDialogTitle),
            resourcesProvider.getString(R.string.commonOk),
            resourcesProvider.getString(R.string.commonCancel)
        ) { resultCurrency ->
            resultCurrency?.also { viewModel.onDefaultCurrencySelected(resultCurrency) }
        }
        .show()
    }
}
