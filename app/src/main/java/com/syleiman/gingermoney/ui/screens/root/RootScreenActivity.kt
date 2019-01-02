package com.syleiman.gingermoney.ui.screens.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.common.screensNavigator.ScreensNavigatorInterface
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import com.syleiman.gingermoney.ui.screens.root.dependencyInjection.RootScreenComponent
import kotlinx.coroutines.delay
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

    @Inject
    lateinit var screensNavigator: ScreensNavigatorInterface

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
                        screensNavigator.moveToMainScreen(this)
                    }
                    else {
                        screensNavigator.moveToSetupScreen(this)
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
}
