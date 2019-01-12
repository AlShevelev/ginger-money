package com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.view


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
import com.syleiman.gingermoney.databinding.FragmentSetupMasterPasswordBinding
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.dto.InvalidPasswordLenError
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewActions.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.fragments.viewActions.ShowError
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.viewModel.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.ViewCommand
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import javax.inject.Inject

/**
 * Fragment for setup master-password
 */
class MasterPasswordFragment : Fragment() {

    private lateinit var binding: FragmentSetupMasterPasswordBinding
    private lateinit var viewModel: MasterPasswordViewModel

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

        viewModel = ViewModelProviders.of(this).get(MasterPasswordViewModel::class.java)

        viewModel.command.observe({this.lifecycle}) { processViewCommand(it) }
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.injections.get<SetupActivityComponent>().inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setup_master_password, container, false)

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
                    is InvalidPasswordLenError -> {
                        uiUtils.showError(
                            requireContext(),
                            resourcesProvider.getFormattedString(
                                R.string.passwordLenError,
                                command.error.minPasswordLen,
                                command.error.maxPasswordLen))
                    }

                    is GeneralError -> uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.commonGeneralError))
                }
            }
        }
    }
}
