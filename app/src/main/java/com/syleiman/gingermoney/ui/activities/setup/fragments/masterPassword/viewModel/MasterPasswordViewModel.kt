package com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.viewModel

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.viewActions.MoveToNextCommand
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.viewActions.ShowError
import com.syleiman.gingermoney.ui.common.ViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase

/**
 *
 */
class MasterPasswordViewModel : ViewModelBase<MasterPasswordModelInterface>() {

    /**
     * Our master-password
     */
    val password: MutableLiveData<String> = MutableLiveData()

    /**
     *
     */
    val passwordMaxLen: MutableLiveData<Int> = MutableLiveData()

    /**
     * Direct command for view
     */
    val command: MutableLiveData<ViewCommand> = MutableLiveData()

    /**
     *
     */
    val nextButtonEnabled: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<SetupActivityComponent>().inject(this)

        passwordMaxLen.value = model.passwordMaxLen
        nextButtonEnabled.value = true
    }

    /**
     *
     */
    fun onNextButtonClick() {
        nextButtonEnabled.value = false

        model.savePassword(password.value) { saveResult ->
            nextButtonEnabled.value = true

            command.value = if(saveResult == null) {
                MoveToNextCommand()     // Ok, move to the next page
            }
            else {
                ShowError(saveResult)
            }
        }
    }
}