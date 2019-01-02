package com.syleiman.gingermoney.ui.dependencyInjection

import com.syleiman.gingermoney.ui.common.screensNavigator.ScreensNavigator
import com.syleiman.gingermoney.ui.common.screensNavigator.ScreensNavigatorInterface
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtils
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import dagger.Binds
import dagger.Module

/**
 *
 */
@Module
abstract class UIModuleBinds {
    @Binds
    abstract fun provideUIUtils(utils: UIUtils): UIUtilsInterface

    @Binds
    abstract fun provideScreensNavigator(navigator: ScreensNavigator): ScreensNavigatorInterface
}