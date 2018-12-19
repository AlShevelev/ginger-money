package com.syleiman.gingermoney.application.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacade
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageOperationsInstanceInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.combined.CombinedStorage
import com.syleiman.gingermoney.core.storages.keyValue.storages.inMemory.InMemoryStorage
import com.syleiman.gingermoney.core.storages.keyValue.storages.sharedPreferences.SharedPreferencesStorage
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtils
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtilsInterface
import com.syleiman.gingermoney.core.utils.deviceInfo.DeviceInfoProvider
import com.syleiman.gingermoney.core.utils.deviceInfo.DeviceInfoProviderInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.rsa.EncryptorRSA
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverter
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverterInterface
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Suppress("unused")
@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun provideDeviceInfoProvider(instance: DeviceInfoProvider): DeviceInfoProviderInterface

    @Binds
    abstract fun provideAppResourcesProvider(instance: AppResourcesProvider): AppResourcesProviderInterface

    @Binds
    abstract fun provideCrashlyticsUtils(instance: CrashlyticsUtils): CrashlyticsUtilsInterface

    //region Key-value storage
    /**  */
    @Binds
    abstract fun provideKeyValueStorageFacade(facade: KeyValueStorageFacade): KeyValueStorageFacadeInterface

    /**  */
    @Binds
    abstract fun provideKeyValueStorage(storage: CombinedStorage): StorageInterface

    /**  */
    @Binds
    @ApplicationScope
    @Named("cache")
    abstract fun provideCacheStorage(storage: InMemoryStorage): StorageOperationsInstanceInterface

    /**  */
    @Binds
    @Named("persistent")
    abstract fun providePersistentStorage(storage: SharedPreferencesStorage): StorageOperationsInstanceInterface
    //endregion

    @Binds
    abstract fun provideStringsConverter(converter: StringsConverter): StringsConverterInterface

    @Binds
    @Named("RSA")
    abstract fun provideEncryptor(encryptor: EncryptorRSA): Encryptor
}