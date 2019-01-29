package com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentLoginMasterPasswordBinding
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.dto.InvalidPassword
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.viewModel.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.displayingErrors.TextError
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ShowWarningCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for authentication via master-password.
 */
class MasterPasswordFragment : FragmentBase<FragmentLoginMasterPasswordBinding, MasterPasswordModelInterface, MasterPasswordViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun provideViewModelType(): Class<MasterPasswordViewModel> = MasterPasswordViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_login_master_password

    /**
     *
     */
    override fun inject() = App.injections.get<LoginActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentLoginMasterPasswordBinding, viewModel: MasterPasswordViewModel) {
        binding.viewModel = viewModel
    }

    /**
     *
     */
    override fun onResume() {
        super.onResume()
        viewModel.onActive()
    }

    /**
     *
     */
    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is LoggedInCommand -> navigation.complete(this)

            is ShowErrorCommand -> {
                when(command.error) {
                    is InvalidPassword -> {
                        uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.invalidPasswordError))
                    }

                    is TextError -> uiUtils.showError(requireContext(), command.error.textMessage)
                }
            }

            is ShowWarningCommand -> {
                when(command.warning) {
                    is TextError -> uiUtils.showWarning(requireContext(), command.warning.textMessage)
                }
            }

            is SwitchCommand -> navigation.switch(this)
        }
    }
}
