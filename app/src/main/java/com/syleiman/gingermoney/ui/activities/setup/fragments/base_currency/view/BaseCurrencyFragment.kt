package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentSetupBaseCurrencyBinding
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model.BaseCurrencyModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.view_model.BaseCurrencyViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.view_commands.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for setup base currency
 */
class BaseCurrencyFragment : FragmentBase<FragmentSetupBaseCurrencyBinding, BaseCurrencyModel, BaseCurrencyViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<BaseCurrencyViewModel> = BaseCurrencyViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_setup_base_currency

    override fun inject() = App.injections.get<SetupActivityComponent>().inject(this)

    override fun linkViewModel(binding: FragmentSetupBaseCurrencyBinding, viewModel: BaseCurrencyViewModel) {
        binding.viewModel = viewModel
    }

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToNextCommand -> navigation.moveToNext(this)
        }
    }
}
