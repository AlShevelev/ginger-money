package com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.view_model

import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.view_commands.MoveToNextCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

class MasterPasswordViewModel : ViewModelBase<MasterPasswordModelInterface>() {
    /**
     * Our master-password
     */
    val password: MutableLiveData<String> = MutableLiveData()

    val passwordMaxLen: MutableLiveData<Int> = MutableLiveData()

    val nextButtonEnabled: MutableLiveData<Boolean> = MutableLiveData()

    init {
        App.injections.get<SetupActivityComponent>().inject(this)

        passwordMaxLen.value = model.passwordMaxLen
        nextButtonEnabled.value = true
    }

    fun onNextButtonClick() {
        nextButtonEnabled.value = false

        launch {
            val saveResult = model.savePassword(password.value)

            nextButtonEnabled.value = true

            command.value = saveResult?.let { ShowErrorCommand(it) } ?: MoveToNextCommand()
        }
    }
}