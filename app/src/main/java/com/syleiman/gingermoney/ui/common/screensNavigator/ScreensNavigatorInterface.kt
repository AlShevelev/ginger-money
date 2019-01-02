package com.syleiman.gingermoney.ui.common.screensNavigator

import com.syleiman.gingermoney.ui.screens.root.RootScreenActivity

/**
 * Navigation between screens
 */
interface ScreensNavigatorInterface {
    /**
     *
     */
    fun moveToMainScreen(context: RootScreenActivity)

    /**
     *
     */
    fun moveToSetupScreen(context: RootScreenActivity)
}