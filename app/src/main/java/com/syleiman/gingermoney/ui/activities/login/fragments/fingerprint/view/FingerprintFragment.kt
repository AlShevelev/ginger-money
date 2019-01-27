package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentLoginFingerprintBinding
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.viewModel.FingerprintViewModel
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.displayingErrors.TextError
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ShowWarningCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 * Fragment for authentication via fingerprint
 */
class FingerprintFragment : Fragment() {
    private lateinit var binding: FragmentLoginFingerprintBinding
    private lateinit var viewModel: FingerprintViewModel

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    @Inject
    internal lateinit var uiUtils: UIUtilsInterface

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FingerprintViewModel::class.java)

        viewModel.command.observe({this.lifecycle}) { processViewCommand(it) }
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.injections.get<LoginActivityComponent>().inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_fingerprint, container, false)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
        return binding.root
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
    private fun processViewCommand(command: ViewCommand) {
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
