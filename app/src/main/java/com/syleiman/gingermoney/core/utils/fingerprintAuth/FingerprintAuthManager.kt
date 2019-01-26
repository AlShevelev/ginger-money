@file:Suppress("DEPRECATION")

package com.syleiman.gingermoney.core.utils.fingerprintAuth

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.Context.FINGERPRINT_SERVICE
import android.content.Context.KEYGUARD_SERVICE
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorFingerprint
import com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.FingerprintAuthEventsHandler
import com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.FingerprintAuthEventsHandlerInterface
import javax.inject.Inject

/**
 *
 */
class FingerprintAuthManager
@Inject
constructor(
    private val appContext: Context,
    private val encryptor: EncryptorFingerprint
) : FingerprintAuthManagerInterface {

    /**
     *
     */
    override val isAuthenticationPossible: Boolean
        get() = checkAuthenticationPossibility()

    /**
     * Returns wrapper to process an authentication
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getEventsHandler(): FingerprintAuthEventsHandlerInterface {
        val manager = getFingerprintManager()
        val cryptoObject = FingerprintManager.CryptoObject(encryptor.getCipher())

        return FingerprintAuthEventsHandler(manager, cryptoObject)
    }

    /**
     *
     */
    private fun checkAuthenticationPossibility(): Boolean {
        // Check SDK
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false
        }

        // Check that the device has a sensor
        val fingerprintManager = getFingerprintManager()
        if (!fingerprintManager.isHardwareDetected) {
            return false
        }

        // Check permission
        if(appContext.checkSelfPermission(Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return false
        }

        // Check that a user stored at least one fingerprint
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            return false
        }

        // Check that a lock screen is enabled
        val keyguardManager = appContext.getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isKeyguardSecure) {
            return false
        }

        return true
    }

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getFingerprintManager() = appContext.getSystemService(FINGERPRINT_SERVICE) as FingerprintManager
}