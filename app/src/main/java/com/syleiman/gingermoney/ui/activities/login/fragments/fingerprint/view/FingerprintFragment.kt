package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentLoginFingerprintBinding
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.viewModel.FingerprintViewModel
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
 * Fragment for authentication via fingerprint
 */
class FingerprintFragment : FragmentBase<FragmentLoginFingerprintBinding, FingerprintModelInterface, FingerprintViewModel>() {
    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun provideViewModelType(): Class<FingerprintViewModel> = FingerprintViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_login_fingerprint

    /**
     *
     */
    override fun inject() = App.injections.get<LoginActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentLoginFingerprintBinding, viewModel: FingerprintViewModel) {
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
