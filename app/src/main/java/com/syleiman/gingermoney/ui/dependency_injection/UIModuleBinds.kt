package com.syleiman.gingermoney.ui.dependency_injection

import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerImpl
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.ui.common.ui_calculator.UICalculatorImpl
import com.syleiman.gingermoney.ui.common.ui_calculator.UICalculator
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtilsImpl
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtils
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class UIModuleBinds {
    @Binds
    abstract fun provideUIUtils(utils: UIUtilsImpl): UIUtils

    @Binds
    abstract fun provideFingerprintAuthenticationFacade(manager: FingerprintAuthManagerImpl): FingerprintAuthManager

    @Binds
    abstract fun provideUICalculator(calculator: UICalculatorImpl): UICalculator
}