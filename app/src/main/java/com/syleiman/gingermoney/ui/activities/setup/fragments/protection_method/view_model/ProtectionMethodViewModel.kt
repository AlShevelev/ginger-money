package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model.ProtectionMethodModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.view_commands.MoveToNextCommand
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand

/**
 *
 */
class ProtectionMethodViewModel : ViewModelBase<ProtectionMethodModelInterface>() {

    /**
     * Selected protection method
     */
    val appProtectionMethod: MutableLiveData<AppProtectionMethod> = MutableLiveData()

    /**
     *
     */
    val finishButtonEnabled: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *
     */
    val fingerprintMethodVisibility: MutableLiveData<Int> = MutableLiveData()

    /**
     *
     */
    init {
        App.injections.get<SetupActivityComponent>().inject(this)

        appProtectionMethod.value = model.startProtectionMethod
        finishButtonEnabled.value = true
        fingerprintMethodVisibility.value = if(model.isFingerprintAuthenticationPossible) View.VISIBLE else View.GONE
    }

    /**
     *
     */
    fun onFinishButtonClick() {
        finishButtonEnabled.value = false

        model.saveProtectionMethod(appProtectionMethod.value!!) { saveResult ->
            finishButtonEnabled.value = true

            command.value = if(saveResult == null) {
                MoveToNextCommand()     // Ok, move to the next page
            }
            else {
                ShowErrorCommand(saveResult)
            }
        }
    }
}