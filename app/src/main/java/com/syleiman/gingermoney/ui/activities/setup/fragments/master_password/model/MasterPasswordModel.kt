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
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun savePassword(password: String?, resultCall: (DisplayingError?) -> Unit) {
        val passwordMinLen = resourcesProvider.getInt(R.integer.masterPasswordMinLen)

        if(password == null || password.length !in passwordMinLen .. passwordMaxLen) {
            resultCall(InvalidPasswordLenError(passwordMinLen, passwordMaxLen))
        }
        else {
            launch {
                val operationResult = try {
                    withContext(Dispatchers.IO) {
                        keyValueStorage.setMasterPassword(encryptor.encrypt(stringsConverter.toBytes(password))!!)
                        null
                    }
                }
                catch(ex: Exception) {
                    ex.printStackTrace()
                    GeneralError()
                }

                if(isActive) {
                    resultCall(operationResult)
                }
            }
        }
    }
}