package com.syleiman.gingermoney.core.utils.app_resources

import android.graphics.drawable.Drawable
import androidx.annotation.*
import com.syleiman.gingermoney.core.global_entities.date_time.DateTimeFormat
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.dto.enums.Color
import org.threeten.bp.DayOfWeek

interface AppResourcesProvider {
    fun getLocale(): String

    fun getString(@StringRes resId: Int): String

    /**
     * Gets string and formats it
     */
    fun getFormattedString(@StringRes resId: Int, vararg args: Any): String

    fun getInt(@IntegerRes resId: Int): Int

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int

    @ColorInt
    fun getColor(color: Color): Int

    fun getBool(@BoolRes boolResId: Int): Boolean

    /**
     * Get dimension value in pixels
     */
    fun getDimension(@DimenRes resId: Int): Float

    fun getDrawable(@DrawableRes resId: Int) : Drawable

    /**
     * Gets metadata value as string
     */
    fun getMetadataValue(key: String): Any?

    /**
     * Returns string value for an app protection method
     */
    fun getAppProtectionMethodString(protectionMethod: AppProtectionMethod): String

    /**
     * Returns string value for a day of week
     */
    fun getDayOfWeekString(dayOfWeek: DayOfWeek): String

    /**
     * Returns string value for an account group
     */
    fun getAccountGroupString(group: AccountGroup): String

    fun getDateTimeFormat(format: DateTimeFormat): String
}