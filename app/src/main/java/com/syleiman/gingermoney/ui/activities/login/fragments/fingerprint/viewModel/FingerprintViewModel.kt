package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.viewModel

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.events.*
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.common.displayingErrors.TextError
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ShowWarningCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 *
 */
class FingerprintViewModel : ViewModelBase<FingerprintModelInterface>() {

    private val fingerprintAuthEventHandler: FingerprintAuthEventHandler = { processAuthEvents(it) }

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    /**
     * Direct command for view
     */
    val command: MutableLiveData<ViewCommand> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<LoginActivityComponent>().inject(this)
    }

    /**
     *
     */
    fun onActive() {
        model.startAuth(fingerprintAuthEventHandler)
    }

    /**
     *
     */
    fun onUseMasterPasswordButtonClick() {
        command.value = SwitchCommand()
    }

    /**
     *
     */
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