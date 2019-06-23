package com.syleiman.gingermoney.application.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.works.WorksManagerImpl
import com.syleiman.gingermoney.core.works.WorksManager
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeImpl
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeImpl
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.storages.Storage
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageOperationsInstance
import com.syleiman.gingermoney.core.storages.key_value.storages.combined.CombinedStorage
import com.syleiman.gingermoney.core.storages.key_value.storages.in_memory.InMemoryStorage
import com.syleiman.gingermoney.core.storages.key_value.storages.shared_preferences.SharedPreferencesStorage
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderImpl
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtilsImpl
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtils
import com.syleiman.gingermoney.core.utils.device_info.DeviceInfoProviderImpl
import com.syleiman.gingermoney.core.utils.device_info.DeviceInfoProvider
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAES
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorFingerprint
import com.syleiman.gingermoney.core.utils.encryption.rsa.EncryptorRSA
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterImpl
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverter
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Suppress("unused")
@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideDeviceInfoProvider(instance: DeviceInfoProviderImpl): DeviceInfoProvider

    @Binds
    abstract fun provideAppResourcesProvider(instance: AppResourcesProviderImpl): AppResourcesProvider

    @Binds
    @ApplicationScope
    abstract fun provideCrashlyticsUtils(instance: CrashlyticsUtilsImpl): CrashlyticsUtils

    //region Key-value storage
    @Binds
    abstract fun provideKeyValueStorageFacade(facade: KeyValueStorageFacadeImpl): KeyValueStorageFacade

    @Binds
    abstract fun provideKeyValueStorage(storage: CombinedStorage): Storage

    @Binds
    @ApplicationScope
    @Named("cache")
    abstract fun provideCacheStorage(storage: InMemoryStorage): StorageOperationsInstance

    @Binds
    @Named("persistent")
    abstract fun providePersistentStorage(storage: SharedPreferencesStorage): StorageOperationsInstance
    //endregion

    @Binds
    abstract fun provideStringsConverter(converter: StringsConverterImpl): StringsConverter

    @Binds
    @Named("RSA")
    abstract fun provideEncryptor(encryptor: EncryptorRSA): Encryptor

    @Binds
    abstract fun provideEncryptorForFingerprint(encryptor: EncryptorAES): EncryptorFingerprint

    @Binds
    abstract fun provideDbStorageFacade(facade: DbStorageFacadeImpl): DbStorageFacade

    @Binds
    abstract fun provideJobsManager(manager: WorksManagerImpl): WorksManager
}