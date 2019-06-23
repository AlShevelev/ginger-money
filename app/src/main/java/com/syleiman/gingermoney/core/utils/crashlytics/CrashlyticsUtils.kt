package com.syleiman.gingermoney.core.utils.crashlytics

interface CrashlyticsUtils {
    fun registerDeviceInfo()

    fun registerAppInfo()

    fun log(tag: String, message: String)

    fun log(ex: Throwable)
}