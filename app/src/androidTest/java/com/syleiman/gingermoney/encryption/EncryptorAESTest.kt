package com.syleiman.gingermoney.encryption

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeImpl
import com.syleiman.gingermoney.core.storages.key_value.storages.in_memory.InMemoryStorage
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAES
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAESOldApi
import com.syleiman.gingermoney.core.utils.encryption.rsa.EncryptorRSA
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterImpl
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverter
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@RequiresApi(Build.VERSION_CODES.M)
class EncryptorAESTest: EncryptorTestBase() {
    companion object {
        private lateinit var converterInstance: StringsConverter
        private lateinit var encryptionUtilsInstance: Encryptor

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converterInstance = StringsConverterImpl()

            encryptionUtilsInstance = if(Build.VERSION.SDK_INT >= 23) {     // New encryptor
                EncryptorAES()
            } else {                                                          // Old encryptor
                val encryptor = EncryptorRSA(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)

                val storage = InMemoryStorage()
                val storageFacade = KeyValueStorageFacadeImpl(storage, converterInstance)

                EncryptorAESOldApi(storageFacade, encryptor)
            }
        }
    }

    override val converter: StringsConverter
        get() = converterInstance

    override val encryptionUtils: Encryptor
        get() = encryptionUtilsInstance
}