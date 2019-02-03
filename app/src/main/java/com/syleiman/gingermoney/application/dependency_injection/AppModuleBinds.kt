package com.syleiman.gingermoney.application.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManager
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.works.WorksManager
import com.syleiman.gingermoney.core.works.WorksManagerInterface
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageInterface
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageOperationsInstanceInterface
import com.syleiman.gingermoney.core.storages.key_value.storages.combined.CombinedStorage
import com.syleiman.gingermoney.core.storages.key_value.storages.in_memory.InMemoryStorage
import com.syleiman.gingermoney.core.storages.key_value.storages.shared_preferences.SharedPreferencesStorage
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtils
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtilsInterface
import com.syleiman.gingermoney.core.utils.device_info.DeviceInfoProvider
import com.syleiman.gingermoney.core.utils.device_info.DeviceInfoProviderInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorAES
import com.syleiman.gingermoney.core.utils.encryption.aes.EncryptorFingerprint
import com.syleiman.gingermoney.core.utils.encryption.rsa.EncryptorRSA
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverter
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterInterface
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

    @Binds
    abstract fun provideEncryptorForFingerprint(encryptor: EncryptorAES): EncryptorFingerprint

    @Binds
    abstract fun provideDbStorageFacade(facade: DbStorageFacade): DbStorageFacadeInterface

    @Binds
    abstract fun provideMainLaunchManager(manager: MainLaunchManager): MainLaunchManagerInterface

    @Binds
    abstract fun provideJobsManager(manager: WorksManager): WorksManagerInterface
}