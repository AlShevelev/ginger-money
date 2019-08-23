package com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverter
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.dto.InvalidPasswordError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class MasterPasswordModelImpl
@Inject
constructor(
    @Named("AES") private val encryptor: Encryptor,
    private val keyValueStorage: KeyValueStorageFacade,
    private val stringsConverter: StringsConverter,
    fingerprintAuthManager: FingerprintAuthManager,
    resourcesProvider: AppResourcesProvider
) : ModelBaseImpl(),
    MasterPasswordModel {

    override val isFingerprintAuthenticationPossible: Boolean = fingerprintAuthManager.isAuthenticationPossible

    override val passwordMaxLen: Int = resourcesProvider.getInt(R.integer.masterPasswordMaxLen)

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    override suspend fun login(password: String?): DisplayingError? =
        if (password.isNullOrEmpty()) {
            InvalidPasswordError()
        } else {
            withContext(Dispatchers.IO) {
                try {
                    val storedPassword = stringsConverter.fromBytes(encryptor.decrypt(keyValueStorage.getMasterPassword())!!)
                    if (password != storedPassword) {
                        InvalidPasswordError()
                    } else {
                        null
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    GeneralError()
                }
            }
        }
}