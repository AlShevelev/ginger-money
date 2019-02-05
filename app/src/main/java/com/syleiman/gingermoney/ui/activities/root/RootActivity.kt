package com.syleiman.gingermoney.ui.activities.root

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.activities.login.LoginActivity
import com.syleiman.gingermoney.ui.activities.main.MainActivity
import com.syleiman.gingermoney.ui.activities.root.dependency_injection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.SetupActivity
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtilsInterface
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 *
 */
class RootActivity : CoroutineScope, AppCompatActivity() {

    @Inject
    lateinit var keyValueStorage: KeyValueStorageFacadeInterface

    @Inject
    lateinit var uiUtils: UIUtilsInterface

    private lateinit var scopeJob: Job

    /**
     * Context of this scope.
     */
    override val coroutineContext: CoroutineContext
        get() = scopeJob + Dispatchers.Main

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

        scopeJob = SupervisorJob()

        launch {
            try {
                val appState = withContext(Dispatchers.IO) {
                    Pair(keyValueStorage.isAppSetupComplete(), keyValueStorage.getAppProtectionMethod())
                }

                val isAppSetupComplete = appState.first
                val appProtectionMethod = appState.second

                if (isAppSetupComplete) {
                    if (appProtectionMethod!! == AppProtectionMethod.WITHOUT_PROTECTION) {
                        moveTo(MainActivity::class.java)
                    } else {
                        moveToLogin(appProtectionMethod)
                    }
                } else {
                    moveTo(SetupActivity::class.java)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                finishAndRemoveTask()
            }
        }
    }

    /**
     *
     */
    override fun onStop() {
        super.onStop()

        scopeJob.cancelChildren()
        scopeJob.cancel()
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