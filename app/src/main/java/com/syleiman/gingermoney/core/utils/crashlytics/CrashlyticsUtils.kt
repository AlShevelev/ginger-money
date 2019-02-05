package com.syleiman.gingermoney.core.utils.crashlytics

import com.crashlytics.android.Crashlytics
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.core.utils.device_info.DeviceInfoProviderInterface
import javax.inject.Inject
import dagger.Lazy

class CrashlyticsUtils
@Inject
constructor(
    private val resourcesService: AppResourcesProviderInterface,
    private val displayInfoService: Lazy<DeviceInfoProviderInterface>
) : CrashlyticsUtilsInterface {

    private val isCrashlyticsEnabled =
        resourcesService.getMetadataValue("firebase_crashlytics_collection_enabled")?.let { it as Boolean} ?: true

    companion object {
        private const val LOCALE_KEY = "LOCALE"

        private const val DISPLAY_SIZE_PIXELS_KEY = "DISPLAY_SIZE_PIXELS"
        private const val DISPLAY_SIZE_DP_KEY = "DISPLAY_SIZE_DP"
        private const val DISPLAY_SIZE_CATEGORY_KEY = "DISPLAY_SIZE_CATEGORY"
        private const val DISPLAY_DENSITY_CATEGORY_KEY = "DISPLAY_DENSITY_CATEGORY"
    }

    /** */
    override fun registerDeviceInfo() {
        if(!isCrashlyticsEnabled) {
            return
        }

        displayInfoService.get().displaySizeInPx.apply {
            Crashlytics.setString(DISPLAY_SIZE_PIXELS_KEY, "${this.width}x${this.height} [pix]")
        }

        displayInfoService.get().displaySizeInDp.apply {
            Crashlytics.setString(DISPLAY_SIZE_DP_KEY, "${this.width}x${this.height} [dp]")
        }

        displayInfoService.get().displayDensityCategory.apply {
            Crashlytics.setString(DISPLAY_DENSITY_CATEGORY_KEY, this.toString())
        }

        displayInfoService.get().displaySizeCategory.apply {
            Crashlytics.setString(DISPLAY_SIZE_CATEGORY_KEY, this.toString())
        }
    }

    /** */
    override fun registerAppInfo() {
        if(!isCrashlyticsEnabled) {
            return
        }

        Crashlytics.setString(LOCALE_KEY, resourcesService.getLocale())
    }

    /**
     *
     */
    override fun log(tag: String, message: String) {
        if(!isCrashlyticsEnabled) {
            return
        }

        Crashlytics.log(0, tag, message)
    }

    /**
     *
     */
    override fun log(ex: Throwable) {
        if(!isCrashlyticsEnabled) {
            return
        }

        Crashlytics.logException(ex)
    }
}