package com.syleiman.gingermoney.ui.activities.root

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.activities.login.LoginActivity
import com.syleiman.gingermoney.ui.activities.main.MainActivity
import com.syleiman.gingermoney.ui.activities.root.dependencyInjection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.SetupActivity
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import javax.inject.Inject

/**
 *
 */
class RootActivity : AppCompatActivity() {

    @Inject
    internal lateinit var mainLaunchManager: MainLaunchManagerInterface

    @Inject
    lateinit var keyValueStorage: KeyValueStorageFacadeInterface

    @Inject
    lateinit var uiUtils: UIUtilsInterface

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.injections.get<RootActivityComponent>().inject(this)
    }

    /**
     *
     */
    override fun onStart() {
        super.onStart()

        mainLaunchManager.restart()
        mainLaunchManager.launchFromUI (
            action = {
                Pair(keyValueStorage.isAppSetupComplete(), keyValueStorage.getAppProtectionMethod())
            },
            resultCallback = { appState ->
                if(appState == null) {
                    uiUtils.showError(this, R.string.commonGeneralError)
                    finishAndRemoveTask()
                }
                else {
                    val isAppSetupComplete = appState.first
                    val appProtectionMethod = appState.second

                    if(isAppSetupComplete) {
                        if(appProtectionMethod!! == AppProtectionMethod.WITHOUT_PROTECTION) {
                            moveTo(MainActivity::class.java)
                        }
                        else {
                            moveToLogin(appProtectionMethod)
                        }
                    }
                    else {
                        moveTo(SetupActivity::class.java)
                    }
                }
            })
    }

    /**
     *
     */
    override fun onStop() {
        super.onStop()

        mainLaunchManager.cancel()
    }

    /**
     *
     */
    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            App.injections.release<RootActivityComponent>()
        }
    }

    /**
     *
     */
    private fun moveTo(targetActivity: Class<*>) {
        // We don't need some complex navigation logic for this activity, so we should not use Navigation Component
        startActivity(Intent(this, targetActivity))
        finish()
    }

    /**
     *
     */
    private fun moveToLogin(appProtectionMethod: AppProtectionMethod) {
        startActivity(LoginActivity.createStartIntent(this, appProtectionMethod))
        finish()
    }
}