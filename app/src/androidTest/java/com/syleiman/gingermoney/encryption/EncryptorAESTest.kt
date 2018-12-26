package com.syleiman.gingermoney.encryption

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacade
import com.syleiman.gingermoney.core.storages.keyValue.storages.inMemory.InMemoryStorage
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAES
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAESOldApi
import com.syleiman.gingermoney.core.utils.encryption.rsa.EncryptorRSA
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverter
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverterInterface
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@RequiresApi(Build.VERSION_CODES.M)
class EncryptorAESTest: EncryptorTestBase() {
    companion object {
        private lateinit var converterInstance: StringsConverterInterface
        private lateinit var encryptionUtilsInstance: Encryptor

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converterInstance = StringsConverter()

            encryptionUtilsInstance = if(Build.VERSION.SDK_INT >= 23) {     // New encryptor
                EncryptorAES()
            }
            else {                                                          // Old encryptor
                val encryptor =
                    EncryptorRSA(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)

                val storage = InMemoryStorage()
                val storageFacade = KeyValueStorageFacade(storage, converterInstance)

                EncryptorAESOldApi(storageFacade, encryptor)
            }
        }
    }

    override val converter: StringsConverterInterface
        get() = converterInstance

    override val encryptionUtils: Encryptor
        get() = encryptionUtilsInstance
}