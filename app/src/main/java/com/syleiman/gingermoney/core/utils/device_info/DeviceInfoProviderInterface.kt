package com.syleiman.gingermoney.core.utils.device_info

import android.util.Size

interface DeviceInfoProviderInterface {
    /** Size of the display in pixels */
    val displaySizeInPx: Size

    /** Size of the display in DIP */
    val displaySizeInDp: Size

    /** */
    val displaySizeCategory: DisplaySizeCategory

    /** */
    val displayDensityCategory: DisplayDensityCategory
}