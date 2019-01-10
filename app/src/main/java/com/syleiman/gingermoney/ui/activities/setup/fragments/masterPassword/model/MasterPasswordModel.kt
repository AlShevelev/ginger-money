package com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverterInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.dto.InvalidPasswordLenError
import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
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
    launchManager: MainLaunchManagerInterface,
    private val stringsConverter: StringsConverterInterface
) : ModelBase(launchManager),
    MasterPasswordModelInterface {

    /**
     *
     */
    override val passwordMaxLen: Int
        get() = resourcesProvider.getInt(R.integer.masterPasswordMaxLen)

    /**
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun savePassword(password: String?, result: (DisplayingError?) -> Unit) {
        val passwordMinLen = resourcesProvider.getInt(R.integer.masterPasswordMinLen)

        if(password == null || password.length !in passwordMinLen .. passwordMaxLen) {
            result(InvalidPasswordLenError(passwordMinLen, passwordMaxLen))
        }
        else {
            launchManager.launchFromUIWithException(
                action =  {
                    keyValueStorage.setMasterPassword(encryptor.encrypt(stringsConverter.toBytes(password))!!)
                },
                resultCallback = { ex ->
                    result(ex?.let { GeneralError() })
                })
        }
    }
}