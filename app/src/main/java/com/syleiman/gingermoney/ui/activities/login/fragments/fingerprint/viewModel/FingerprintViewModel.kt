package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.viewModel

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand

/**
 *
 */
class FingerprintViewModel : ViewModelBase<FingerprintModelInterface>() {

    /**
     * Direct command for view
     */
    val command: MutableLiveData<ViewCommand> = MutableLiveData()

    /**
     *
     */
    val buttonsEnabled: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<LoginActivityComponent>().inject(this)

        buttonsEnabled.value = true
    }

    /**
     *
     */
    fun onUseMasterPasswordButtonClick() {
        command.value = SwitchCommand()
    }
}