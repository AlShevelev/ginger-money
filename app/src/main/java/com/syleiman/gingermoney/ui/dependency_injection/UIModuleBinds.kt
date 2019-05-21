package com.syleiman.gingermoney.ui.dependency_injection

import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.ui.common.ui_calculator.UICalculator
import com.syleiman.gingermoney.ui.common.ui_calculator.UICalculatorInterface
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtils
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtilsInterface
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

    @Binds
    abstract fun provideUICalculator(calculator: UICalculator): UICalculatorInterface
}