package com.syleiman.gingermoney.application

import android.annotation.SuppressLint
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.syleiman.gingermoney.application.dependencyInjection.AppComponent
import com.syleiman.gingermoney.application.dependencyInjection.DependencyInjectionStorage
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtilsInterface
import javax.inject.Inject

class App: Application() {
    @Inject
    internal lateinit var crashlyticsUtils: CrashlyticsUtilsInterface

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var injections : DependencyInjectionStorage
        private set
    }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        injections = DependencyInjectionStorage(applicationContext)
        injections.get<AppComponent>().inject(this)

        crashlyticsUtils.registerAppInfo()
        crashlyticsUtils.registerDeviceInfo()
    }
}