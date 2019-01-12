package com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.databinding.FragmentSetupBaseCurrencyBinding
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.viewModel.BaseCurrencyViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewActions.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewActions.ShowError
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.ViewCommand
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import kotlinx.android.synthetic.main.fragment_setup_base_currency.*
import javax.inject.Inject

/**
 * Fragment for setup base currency
 */
class BaseCurrencyFragment : Fragment() {

    private lateinit var binding: FragmentSetupBaseCurrencyBinding
    private lateinit var viewModel: BaseCurrencyViewModel

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    @Inject
    internal lateinit var uiUtils: UIUtilsInterface

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(BaseCurrencyViewModel::class.java)

        viewModel.command.observe({this.lifecycle}) { processViewCommand(it) }
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.injections.get<SetupActivityComponent>().inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setup_base_currency, container, false)

        binding.viewModel = viewModel
        return binding.root
    }

    /**
     *
     */
    private fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToNextCommand -> navigation.moveToNext(this)

            is ShowError -> {
                when(command.error) {
                    is GeneralError -> uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.commonGeneralError))
                }
            }
        }
    }
}
