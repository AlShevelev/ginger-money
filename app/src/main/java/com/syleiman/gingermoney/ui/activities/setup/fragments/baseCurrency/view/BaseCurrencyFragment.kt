package com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.view


import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentSetupBaseCurrencyBinding
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.model.BaseCurrencyModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.viewModel.BaseCurrencyViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewCommands.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for setup base currency
 */
class BaseCurrencyFragment : FragmentBase<FragmentSetupBaseCurrencyBinding, BaseCurrencyModelInterface, BaseCurrencyViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun provideViewModelType(): Class<BaseCurrencyViewModel> = BaseCurrencyViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_setup_base_currency

    /**
     *
     */
    override fun inject() = App.injections.get<SetupActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentSetupBaseCurrencyBinding, viewModel: BaseCurrencyViewModel) {
        binding.viewModel = viewModel
    }

    /**
     *
     */
    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToNextCommand -> navigation.moveToNext(this)
        }
    }
}
