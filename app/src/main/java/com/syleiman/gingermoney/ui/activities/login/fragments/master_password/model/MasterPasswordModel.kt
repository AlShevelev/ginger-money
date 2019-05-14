package com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.dto.InvalidPassword
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 *
 */
class MasterPasswordModel
@Inject
constructor(
    @Named("AES") private val encryptor: Encryptor,
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    private val stringsConverter: StringsConverterInterface,
    fingerprintAuthManager: FingerprintAuthManagerInterface,
    resourcesProvider: AppResourcesProviderInterface
) : ModelBase(),
    MasterPasswordModelInterface {
    /**
     *
     */
    override val isFingerprintAuthenticationPossible: Boolean = fingerprintAuthManager.isAuthenticationPossible

    /**
     *
     */
    override val passwordMaxLen: Int = resourcesProvider.getInt(R.integer.masterPasswordMaxLen)

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    override suspend fun login(password: String?): DisplayingError? =
        if (password.isNullOrEmpty()) {
            InvalidPassword()
        } else {
            withContext(Dispatchers.IO) {
                try {
                    val storedPassword = stringsConverter.fromBytes(encryptor.decrypt(keyValueStorage.getMasterPassword())!!)
                    if (password != storedPassword) {
                        InvalidPassword()
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