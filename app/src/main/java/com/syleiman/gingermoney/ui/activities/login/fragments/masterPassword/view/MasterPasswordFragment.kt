package com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.databinding.FragmentLoginMasterPasswordBinding
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.dto.InvalidPassword
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.viewModel.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for authentication via master-password.
 */
class MasterPasswordFragment : Fragment() {
    private lateinit var binding: FragmentLoginMasterPasswordBinding
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
        App.injections.get<LoginActivityComponent>().inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_master_password, container, false)

        binding.viewModel = viewModel
        return binding.root
    }

    /**
     *
     */
    private fun processViewCommand(command: ViewCommand) {
        when(command) {
            is LoggedInCommand -> navigation.complete(this)

            is ShowErrorCommand -> {
                when(command.error) {
                    is InvalidPassword -> {
                        uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.invalidPasswordError))
                    }

                    is GeneralError -> uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.commonGeneralError))
                }
            }

            is SwitchCommand -> navigation.switch(this)
        }
    }
}
