package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view_model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.*
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModel
import com.syleiman.gingermoney.ui.activities.login.fragments.view_commands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.view_commands.SwitchCommand
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.TextError
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowWarningCommand
import javax.inject.Inject

class FingerprintViewModel : ViewModelBase<FingerprintModel>() {

    private val fingerprintAuthEventHandler: FingerprintAuthEventHandler = { processAuthEvents(it) }

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProvider

    init {
        App.injections.get<LoginActivityComponent>().inject(this)
    }

    fun onActive() {
        model.startAuth(fingerprintAuthEventHandler)
    }

    fun onUseMasterPasswordButtonClick() {
        command.value = SwitchCommand()
    }

    private fun processAuthEvents(event: FingerprintAuthEvent) {
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