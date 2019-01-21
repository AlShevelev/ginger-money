package com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.view


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
import com.syleiman.gingermoney.databinding.FragmentSetupProtectionMethodBinding
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.viewModel.ProtectionMethodViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewCommands.MoveToNextCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import javax.inject.Inject

/**
 * Fragment for setup protection method for the app
 */
class ProtectionMethodFragment : Fragment() {
    private lateinit var binding: FragmentSetupProtectionMethodBinding
    private lateinit var viewModel: ProtectionMethodViewModel

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

        viewModel = ViewModelProviders.of(this).get(ProtectionMethodViewModel::class.java)

        viewModel.command.observe({this.lifecycle}) { processViewCommand(it) }
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.injections.get<SetupActivityComponent>().inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setup_protection_method, container, false)

        binding.viewModel = viewModel
        return binding.root
    }

    /**
     *
     */
    private fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToNextCommand -> navigation.moveToNext(this)

            is ShowErrorCommand -> {
                when(command.error) {
                    is GeneralError -> uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.commonGeneralError))
                }
            }
        }
    }
}
