package com.syleiman.gingermoney.ui.activities.login.fragments.master_password.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.*
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.view_commands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.view_commands.SwitchCommand
import com.syleiman.gingermoney.ui.common.displaying_errors.TextError
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.view_commands.ShowWarningCommand
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 */
class MasterPasswordViewModel : ViewModelBase<MasterPasswordModelInterface>() {

    private val fingerprintAuthEventHandler: FingerprintAuthEventHandler = { processFingerprintAuthEvents(it) }

    @Inject
    internal lateinit var fingerprintModel: FingerprintModelInterface

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    /**
     * Our master-password
     */
    val password: MutableLiveData<String> = MutableLiveData()

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

        launch {
            val loginResult = model.login(password.value)

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

            is FingerprintAuthFailEvent ->
                command.value = ShowErrorCommand(TextError(resourcesProvider.getString(R.string.enterFingerprintAuthFail)))

            is FingerprintAuthErrorEvent -> event.message?.let { message ->
                command.value = ShowErrorCommand(TextError(message))
            }

            is FingerprintAuthWarningEvent -> event.message?.let { message ->
                command.value = ShowWarningCommand(TextError(message))
            }
        }
    }
}