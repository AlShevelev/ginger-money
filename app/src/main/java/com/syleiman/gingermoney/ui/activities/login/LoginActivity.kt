package com.syleiman.gingermoney.ui.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelper
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    companion object {
        private const val APP_PROTECTION_METHOD = "APP_PROTECTION_METHOD"

        fun createStartIntent(context: Context, appProtectionMethod: AppProtectionMethod): Intent =
            Intent(context, LoginActivity::class.java)
                .apply {
                    this.putExtra(APP_PROTECTION_METHOD, appProtectionMethod.toString())
                }
    }

    @Inject
    internal lateinit var navigation: NavigationHelper

    @Inject
    internal lateinit var fingerprintAuthManager: FingerprintAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        App.injections.get<LoginActivityComponent>().inject(this)

        val defaultProtectionMethod = AppProtectionMethod.from(intent.extras!!.getString(APP_PROTECTION_METHOD)!!)
        when(defaultProtectionMethod) {
            AppProtectionMethod.FINGERPRINT -> {
                if(fingerprintAuthManager.isAuthenticationPossible) {
                    navigation.setFingerprintAsHome(this)
                } else {
                    navigation.setMasterPasswordAsHome(this)
                }
            }

            AppProtectionMethod.MASTER_PASSWORD -> navigation.setMasterPasswordAsHome(this)

            else -> throw UnsupportedOperationException("This protection method is not supported: $defaultProtectionMethod")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            App.injections.release<LoginActivityComponent>()
        }
    }
}
