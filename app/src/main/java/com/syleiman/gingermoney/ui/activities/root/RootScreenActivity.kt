package com.syleiman.gingermoney.ui.activities.root

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.ui.activities.main.MainActivity
import com.syleiman.gingermoney.ui.activities.root.dependencyInjection.RootScreenComponent
import com.syleiman.gingermoney.ui.activities.setup.SetupActivity
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import javax.inject.Inject

/**
 *
 */
class RootScreenActivity : AppCompatActivity() {

    @Inject
    internal lateinit var mainLaunchManager: MainLaunchManagerInterface

    @Inject
    lateinit var keyValueStorageFacade: KeyValueStorageFacadeInterface

    @Inject
    lateinit var uiUtils: UIUtilsInterface

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.injections.get<RootScreenComponent>().inject(this)
    }

    /**
     *
     */
    override fun onStart() {
        super.onStart()

        mainLaunchManager.restart()
        mainLaunchManager.launchFromUI (
            action = {
                keyValueStorageFacade.isAppSetupComplete()
            },
            resultCallback = { isAppSetupComplete ->
                if(isAppSetupComplete == null) {
                    uiUtils.showError(this, R.string.commonGeneralError)
                    finishAndRemoveTask()
                }
                else {
                    if(isAppSetupComplete) {
                        moveTo(MainActivity::class.java)
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
            App.injections.release<RootScreenComponent>()
        }
    }

    /**
     *
     */
    private fun moveTo(targetActivity: Class<*>) {
        // We don't need some complex navigation logic for this activity
        val intent = Intent(this, targetActivity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(intent)
    }
}
