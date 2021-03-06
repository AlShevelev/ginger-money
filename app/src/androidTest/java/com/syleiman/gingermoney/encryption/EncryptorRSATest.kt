package com.syleiman.gingermoney.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.rsa.EncryptorRSA
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterImpl
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverter
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EncryptorRSATest: EncryptorTestBase() {

    companion object {
        private lateinit var converterInstance: StringsConverter
        private lateinit var encryptionUtilsInstance: Encryptor

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converterInstance = StringsConverterImpl()
            encryptionUtilsInstance =
                    EncryptorRSA(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
        }
    }

    override val converter: StringsConverter
        get() = converterInstance

    override val encryptionUtils: Encryptor
        get() = encryptionUtilsInstance
}