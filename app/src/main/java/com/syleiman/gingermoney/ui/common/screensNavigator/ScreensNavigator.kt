package com.syleiman.gingermoney.ui.common.screensNavigator

import android.content.Intent
import com.syleiman.gingermoney.ui.screens.main.MainActivity
import com.syleiman.gingermoney.ui.screens.root.RootScreenActivity
import com.syleiman.gingermoney.ui.screens.setup.SetupActivity
import javax.inject.Inject

/**
 * Navigation between screens
 */
class ScreensNavigator
@Inject
constructor() : ScreensNavigatorInterface {
    /**
     *
     */
    override fun moveToMainScreen(context: RootScreenActivity) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    /**
     *
     */
    override fun moveToSetupScreen(context: RootScreenActivity) {
        val intent = Intent(context, SetupActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }
}