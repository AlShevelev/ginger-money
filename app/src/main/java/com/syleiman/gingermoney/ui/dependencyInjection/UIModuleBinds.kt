package com.syleiman.gingermoney.ui.dependencyInjection

import com.syleiman.gingermoney.core.utils.fingerprintAuth.FingerprintAuthManager
import com.syleiman.gingermoney.core.utils.fingerprintAuth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtils
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import dagger.Binds
import dagger.Module

/**
 *
 */
@Suppress("unused")
@Module
abstract class UIModuleBinds {
    @Binds
    abstract fun provideUIUtils(utils: UIUtils): UIUtilsInterface

    @Binds
    abstract fun provideFingerprintAuthenticationFacade(manager: FingerprintAuthManager): FingerprintAuthManagerInterface
}