package com.syleiman.gingermoney.ui.activities.login.fragments.master_password.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentLoginMasterPasswordBinding
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.dto.InvalidPassword
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.view_model.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.login.fragments.view_commands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.view_commands.SwitchCommand
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.displaying_errors.TextError
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.view_commands.ShowWarningCommand
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for authentication via master-password.
 */
class MasterPasswordFragment : FragmentBase<FragmentLoginMasterPasswordBinding, MasterPasswordModel, MasterPasswordViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<MasterPasswordViewModel> = MasterPasswordViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_login_master_password

    override fun inject() = App.injections.get<LoginActivityComponent>().inject(this)

    override fun linkViewModel(binding: FragmentLoginMasterPasswordBinding, viewModel: MasterPasswordViewModel) {
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.onActive()
    }

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is LoggedInCommand -> navigation.complete(this)

            is ShowErrorCommand -> {
                when(command.error) {
                    is InvalidPassword -> {
                        uiUtils.showError(resourcesProvider.getString(R.string.invalidPasswordError))
                    }

                    is TextError -> uiUtils.showError(command.error.textMessage)
                }
            }

            is ShowWarningCommand -> {
                when(command.warning) {
                    is TextError -> uiUtils.showWarning(command.warning.textMessage)
                }
            }

            is SwitchCommand -> navigation.switch(this)
        }
    }
}
