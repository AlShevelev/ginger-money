package com.syleiman.gingermoney.application

import android.annotation.SuppressLint
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.shevelev.alpha_emoji_panel.EmojiInitializer
import com.syleiman.gingermoney.application.dependency_injection.AppComponent
import com.syleiman.gingermoney.application.dependency_injection.DependencyInjectionStorage
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtils
import javax.inject.Inject

class App: Application() {

    @Inject
    internal lateinit var crashlyticsUtils: CrashlyticsUtils

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

        EmojiInitializer.init(this)
    }
}