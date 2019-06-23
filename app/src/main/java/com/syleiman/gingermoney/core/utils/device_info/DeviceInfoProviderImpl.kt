package com.syleiman.gingermoney.core.utils.device_info

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.util.Size
import com.syleiman.gingermoney.core.helpers.minIndexBy
import javax.inject.Inject

class DeviceInfoProviderImpl
@Inject
constructor(private val appContext: Context
) : DeviceInfoProvider {

    /** Size of the display in pixels */
    override val displaySizeInPx: Size
        get() = appContext.resources.displayMetrics.let {
            Size(it.widthPixels, it.heightPixels)
        }

    /** Size of the display in DIP */
    override val displaySizeInDp: Size
        get() = appContext.resources.displayMetrics.let {
            Size((it.widthPixels / it.density).toInt(), (it.heightPixels / it.density).toInt())
        }

    override val displaySizeCategory: DisplaySizeCategory
        get() = appContext.resources.configuration.screenLayout.let {
            when(it and Configuration.SCREENLAYOUT_SIZE_MASK) {
                Configuration.SCREENLAYOUT_SIZE_XLARGE -> DisplaySizeCategory.XLARGE
                Configuration.SCREENLAYOUT_SIZE_LARGE -> DisplaySizeCategory.LARGE
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> DisplaySizeCategory.NORMAL
                Configuration.SCREENLAYOUT_SIZE_SMALL -> DisplaySizeCategory.SMALL
                else -> DisplaySizeCategory.UNDEFINED
            }
        }

    override val displayDensityCategory: DisplayDensityCategory
        get() = appContext.resources.displayMetrics.let { metrics ->
            val defaultScale = 1F / DisplayMetrics.DENSITY_DEFAULT

            val densityFactors = listOf(
                DisplayMetrics.DENSITY_LOW,
                DisplayMetrics.DENSITY_MEDIUM,
                DisplayMetrics.DENSITY_HIGH,
                DisplayMetrics.DENSITY_XHIGH,
                DisplayMetrics.DENSITY_XXHIGH,
                DisplayMetrics.DENSITY_XXXHIGH)

            val densityIndex = densityFactors.minIndexBy {
                    densityFactor, _ -> Math.abs(densityFactor * defaultScale - metrics.scaledDensity)
            }

            return when(densityIndex) {
                0 -> DisplayDensityCategory.LDPI
                1 -> DisplayDensityCategory.MDPI
                2 -> DisplayDensityCategory.HDPI
                3 -> DisplayDensityCategory.XHDPI
                4 -> DisplayDensityCategory.XXHDPI
                5 -> DisplayDensityCategory.XXXHDPI
                else -> throw UnsupportedOperationException("Invalid density index: $densityIndex")
            }
        }
}