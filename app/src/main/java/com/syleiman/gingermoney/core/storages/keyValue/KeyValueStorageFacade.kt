package com.syleiman.gingermoney.core.storages.keyValue

import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageInterface
import javax.inject.Inject

/** Helper class for access to App-level private shared preferences  */
class KeyValueStorageFacade
@Inject
constructor(
    private val keyValueStorage: StorageInterface
) : KeyValueStorageFacadeInterface {

    // note[AS] How to use
    /** */
//    override fun saveUserCredentials(userCredentialsInfo: UserCredentialsBriefInfo) =
//        keyValueStorage.update {
//            it.remove(CREDENTIALS_USER_NAME_KEY)
//
//            it.putString(CREDENTIALS_USER_NAME_KEY_NEW, userCredentialsInfo.userName)
//            it.putBytes(CREDENTIALS_PASSWORD_KEY, keystoreService.encrypt(userCredentialsInfo.password))
//        }

    /** */
//    override fun isUserPasswordStored(): Boolean =
//        keyValueStorage.read {
//            it.contains(CREDENTIALS_PASSWORD_KEY)
//        }

    /** */
//    override fun getUserCredentials(): UserCredentialsBriefInfo =
//        keyValueStorage.read {
//            var login = it.readString(CREDENTIALS_USER_NAME_KEY_NEW)
//            if(login == null) {
//                login = keystoreService.decrypt(it.readBytes(CREDENTIALS_USER_NAME_KEY))
//            }
//
//            val password = keystoreService.decrypt(it.readBytes(CREDENTIALS_PASSWORD_KEY))
//
//            UserCredentialsBriefInfo(login, password)
//        }


    /** Remove info of current user */
//    override fun removeCurrentUserInfo() {
//        keyValueStorage.update {
//            it.remove(CURRENT_USER_PROFILE_ID_KEY)
//            it.remove(CURRENT_USER_NAME_KEY)
//            it.remove(CREDENTIALS_USER_NAME_KEY_NEW)
//            it.remove(CURRENT_USER_AVATAR_ID_KEY)
//        }
//    }

}