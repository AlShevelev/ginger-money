package com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.dto.InvalidPasswordLenError
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
    private val resourcesProvider: AppResourcesProviderInterface,
    @Named("AES") private val encryptor: Encryptor,
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    private val stringsConverter: StringsConverterInterface
) : ModelBase(),
    MasterPasswordModelInterface {

    /**
     *
     */
    override val passwordMaxLen: Int = resourcesProvider.getInt(R.integer.masterPasswordMaxLen)

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    override suspend fun savePassword(password: String?): DisplayingError? {
        val passwordMinLen = resourcesProvider.getInt(R.integer.masterPasswordMinLen)

        return if(password == null || password.length !in passwordMinLen .. passwordMaxLen) {
            InvalidPasswordLenError(passwordMinLen, passwordMaxLen)
        } else {
            withContext(Dispatchers.IO) {
                try {
                    keyValueStorage.setMasterPassword(encryptor.encrypt(stringsConverter.toBytes(password))!!)
                    null
                } catch(ex: Exception) {
                    ex.printStackTrace()
                    GeneralError()
                }
            }
        }
    }
}