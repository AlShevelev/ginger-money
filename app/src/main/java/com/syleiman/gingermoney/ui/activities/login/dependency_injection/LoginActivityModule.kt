package com.syleiman.gingermoney.ui.activities.login.dependency_injection

import android.os.Build
import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModel
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelFake
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelInterface
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @Provides
    @ActivityScope
    internal fun provideFingerprintModel(
        launchManager: MainLaunchManagerInterface,
        fingerprintAuthManager: FingerprintAuthManagerInterface): FingerprintModelInterface =

        if(fingerprintAuthManager.isAuthenticationPossible && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintModel(launchManager, fingerprintAuthManager)
        }
        else {
            FingerprintModelFake()
        }
}