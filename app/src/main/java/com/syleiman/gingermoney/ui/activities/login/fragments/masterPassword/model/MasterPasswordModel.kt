package com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.fingerprintAuthentication.FingerprintAuthenticationFacadeInterface
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverterInterface
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.dto.InvalidPassword
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
    @Named("AES") private val encryptor: Encryptor,
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    private val stringsConverter: StringsConverterInterface,
    launchManager: MainLaunchManagerInterface,
    fingerprintAuthenticationFacade: FingerprintAuthenticationFacadeInterface,
    resourcesProvider: AppResourcesProviderInterface
) : ModelBase(launchManager),
    MasterPasswordModelInterface {
    /**
     *
     */
    override val isFingerprintAuthenticationPossible: Boolean = fingerprintAuthenticationFacade.isAuthenticationPossible

    /**
     *
     */
    override val passwordMaxLen: Int = resourcesProvider.getInt(R.integer.masterPasswordMaxLen)

    /**
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun login(password: String?, result: (DisplayingError?) -> Unit) {
        if(password.isNullOrEmpty()) {
            result(InvalidPassword())
        }
        else {
            launchManager.launchFromUIWithException(
                action =  {
                    val storedPassword = stringsConverter.fromBytes(encryptor.decrypt(keyValueStorage.getMasterPassword())!!)
                    return@launchFromUIWithException if(password != storedPassword) {
                        InvalidPassword()
                    }
                    else {
                        null
                    }
                },
                resultCallback = { loginResult, ex ->
                    if(ex != null) {
                        result(GeneralError())
                    }
                    else {
                        result(loginResult)
                    }
                })
        }
    }
}