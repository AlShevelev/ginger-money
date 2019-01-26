package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.viewModel

import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.events.*
import com.syleiman.gingermoney.ui.activities.login.dependencyInjection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.dto.TextStyle
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.LoggedInCommand
import com.syleiman.gingermoney.ui.activities.login.fragments.viewCommands.SwitchCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 *
 */
class FingerprintViewModel : ViewModelBase<FingerprintModelInterface>() {

    // Text styles for Explanation text
    private val normalTextStyle: TextStyle
    private val errorTextStyle: TextStyle

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
    val explanationText: MutableLiveData<String> = MutableLiveData()

    /**
     *
     */
    val explanationTextStyle: MutableLiveData<TextStyle> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<LoginActivityComponent>().inject(this)

        normalTextStyle = TextStyle(resourcesProvider.getColor(R.color.textOnPrimary), Typeface.defaultFromStyle(Typeface.NORMAL))
        errorTextStyle = TextStyle(resourcesProvider.getColor(R.color.red), Typeface.defaultFromStyle(Typeface.BOLD))
    }

    /**
     *
     */
    fun onActive() {
        model.startAuth(fingerprintAuthEventHandler)

        explanationText.value = resourcesProvider.getString(R.string.enterTouchSensor)
        explanationTextStyle.value = normalTextStyle
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

            is FingerprintAuthFailEvent -> Log.d("FingerprintAuthEvent", "Fail!")
            is FingerprintAuthErrorEvent -> Log.d("FingerprintAuthEvent", "Error: ${event.message}")

            is FingerprintAuthWarningEvent ->  {
                explanationText.value = event.message
                explanationTextStyle.value = errorTextStyle
            }
        }
    }
}