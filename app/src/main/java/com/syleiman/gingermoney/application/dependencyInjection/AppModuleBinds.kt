package com.syleiman.gingermoney.application.dependencyInjection

import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtils
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtilsInterface
import com.syleiman.gingermoney.core.utils.deviceInfo.DeviceInfoProvider
import com.syleiman.gingermoney.core.utils.deviceInfo.DeviceInfoProviderInterface
import dagger.Binds
import dagger.Module

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun provideDeviceInfoProvider(instance: DeviceInfoProvider): DeviceInfoProviderInterface

    @Binds
    abstract fun provideAppResourcesProvider(instance: AppResourcesProvider): AppResourcesProviderInterface

    @Binds
    abstract fun provideCrashlyticsUtils(instance: CrashlyticsUtils): CrashlyticsUtilsInterface
}