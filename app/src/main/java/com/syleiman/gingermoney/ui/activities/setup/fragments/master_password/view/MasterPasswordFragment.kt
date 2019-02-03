package com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.view


import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentSetupMasterPasswordBinding
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.dto.InvalidPasswordLenError
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.view_model.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.view_commands.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for setup master-password
 */
class MasterPasswordFragment : FragmentBase<FragmentSetupMasterPasswordBinding, MasterPasswordModelInterface, MasterPasswordViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun provideViewModelType(): Class<MasterPasswordViewModel> = MasterPasswordViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_setup_master_password

    /**
     *
     */
    override fun inject() = App.injections.get<SetupActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentSetupMasterPasswordBinding, viewModel: MasterPasswordViewModel) {
        binding.viewModel = viewModel
    }

    /**
     *
     */
    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToNextCommand -> navigation.moveToNext(this)

            is ShowErrorCommand -> {
                when(command.error) {
                    is InvalidPasswordLenError -> {
                        uiUtils.showError(
                            requireContext(),
                            resourcesProvider.getFormattedString(
                                R.string.passwordLenError,
                                command.error.minPasswordLen,
                                command.error.maxPasswordLen))
                    }
                }
            }
        }
    }
}
