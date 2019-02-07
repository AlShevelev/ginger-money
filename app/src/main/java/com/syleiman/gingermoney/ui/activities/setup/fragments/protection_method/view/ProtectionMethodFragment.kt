package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.view


import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentSetupProtectionMethodBinding
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model.ProtectionMethodModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.view_model.ProtectionMethodViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.view_commands.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for setup protection method for the app
 */
class ProtectionMethodFragment : FragmentBase<FragmentSetupProtectionMethodBinding, ProtectionMethodModelInterface, ProtectionMethodViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun provideViewModelType(): Class<ProtectionMethodViewModel> = ProtectionMethodViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_setup_protection_method

    /**
     *
     */
    override fun inject() = App.injections.get<SetupActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentSetupProtectionMethodBinding, viewModel: ProtectionMethodViewModel) {
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