package com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.events.*
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import javax.inject.Inject

/**
 *
 */
class MasterPasswordViewModel : ViewModelBase<MasterPasswordModelInterface>() {

    private val fingerprintAuthEventHandler: FingerprintAuthEventHandler = { processFingerprintAuthEvents(it) }

    @Inject
    internal lateinit var fingerprintModel: FingerprintModelInterface

    /**
     * Our master-password
     */
    val password: MutableLiveData<String> = MutableLiveData()

    /**
     * Direct command for view
     */
    val command: MutableLiveData<ViewCommand> = MutableLiveData()

    /**
     *
     */
    val fingerprintButtonVisible: MutableLiveData<Int> = MutableLiveData()

    /**
     *
     */
    val passwordMaxLen: MutableLiveData<Int> = MutableLiveData()

    /**
     *
     */
    val buttonsEnabled: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<LoginActivityComponent>().inject(this)

        fingerprintButtonVisible.value = if(model.isFingerprintAuthenticationPossible) View.VISIBLE else View.INVISIBLE
        buttonsEnabled.value = true
        passwordMaxLen.value = model.passwordMaxLen
    }

    /**
     *
     */
    fun onActive() {
        fingerprintModel.startAuth(fingerprintAuthEventHandler)
    }

    /**
     *
     */
    fun onLoginButtonClick() {
        buttonsEnabled.value = false

        model.login(password.value) { loginResult ->
            buttonsEnabled.value = true

            command.value = if(loginResult == null) {
                LoggedInCommand()     // Ok, move to the next page
            }
            else {
                ShowErrorCommand(loginResult)
            }
        }
    }

    /**
     *
     */
    fun onUseFingerprintButtonClick() {
        command.value = SwitchCommand()
    }

    /**
     *
     */
    private fun processFingerprintAuthEvents(event: FingerprintAuthEvent) {
        when(event) {
            is FingerprintAuthSuccessEvent -> command.value = LoggedInCommand()

            is FingerprintAuthFailEvent -> Log.d("FingerprintAuthEvent", "Fail!")
            is FingerprintAuthErrorEvent -> Log.d("FingerprintAuthEvent", "Error: ${event.message}")
            is FingerprintAuthWarningEvent -> Log.d("FingerprintAuthEvent", "Warning: ${event.message}")
        }
    }
}