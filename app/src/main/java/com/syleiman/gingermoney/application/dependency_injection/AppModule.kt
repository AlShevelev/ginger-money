package com.syleiman.gingermoney.application.dependency_injection

import android.content.Context
import android.os.Build
import androidx.room.Room
import com.syleiman.gingermoney.application.dependency_injection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.storages.db.core.DbCore
import com.syleiman.gingermoney.core.storages.db.core.DbCoreRun
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAES
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAESOldApi
import dagger.Module
import dagger.Provides
import javax.inject.Named

/** Application level module - global objects are created here   */
@Module
class AppModule(private val appContext: Context) {

    @Provides
    @ApplicationScope
    internal fun provideContext(): Context = appContext

    @Provides
    @ApplicationScope
    @Named("AES")
    internal fun provideEncryptor(
        keyValueStorageFacade: KeyValueStorageFacade,
        @Named("RSA") encryptor: Encryptor): Encryptor {

        return if (Build.VERSION.SDK_INT >= 23) {
            EncryptorAES()
        } else {
            EncryptorAESOldApi(keyValueStorageFacade, encryptor)
        }
    }

    @Provides
    @ApplicationScope
    internal fun provideRoomDbCore(appContext: Context): DbCoreRun =
        Room
            .databaseBuilder(appContext, DbCore::class.java, "ginger_money.db")
            .build()
}