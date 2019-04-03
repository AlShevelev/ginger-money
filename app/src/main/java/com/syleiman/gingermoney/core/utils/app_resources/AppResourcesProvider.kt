package com.syleiman.gingermoney.core.utils.app_resources

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import org.threeten.bp.DayOfWeek
import java.lang.UnsupportedOperationException
import java.text.MessageFormat
import javax.inject.Inject

class AppResourcesProvider
@Inject
constructor(
    private val appContext: Context
) : AppResourcesProviderInterface {

    /**
     *
     */
    override fun getLocale(): String = getString(R.string.locale)

    /**
     *
     */
    override fun getString(resId: Int): String = appContext.getString(resId)

    /**
     * Gets string and formats it
     */
    override fun getFormattedString(@StringRes resId: Int, vararg args: Any): String =
        when(args.size) {
            1 -> MessageFormat.format(getString(resId), args[0])
            2 -> MessageFormat.format(getString(resId), args[0], args[1])
            3 -> MessageFormat.format(getString(resId), args[0], args[1], args[2])
            else -> throw UnsupportedOperationException("Too many arguments: ${args.size}")
        }


    /**
     *
     */
    override fun getInt(@IntegerRes resId: Int): Int = appContext.resources.getInteger(resId)

    /**
     *
     */
    @Suppress("DEPRECATION")
    @ColorInt
    override fun getColor(@ColorRes resId: Int): Int =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            appContext.resources.getColor(resId, null)
        } else {
            appContext.resources.getColor(resId)
        }

    /**
     * Get metadata value as string
     */
    override fun getMetadataValue(key: String): Any? {
        try {
            return appContext
                .packageManager
                .getApplicationInfo(appContext.packageName, PackageManager.GET_META_DATA)
                .metaData
                .get(key)
        } catch (ex: PackageManager.NameNotFoundException) {
            ex.printStackTrace()
        }

        return null
    }

    /**
     * Returns string value for an app protection method
     */
    override fun getAppProtectionMethodString(protectionMethod: AppProtectionMethod): String =
        when(protectionMethod) {
            AppProtectionMethod.MASTER_PASSWORD -> getString(R.string.passMethodMasterPassword)
            AppProtectionMethod.FINGERPRINT -> getString(R.string.passMethodFingerprint)
            AppProtectionMethod.WITHOUT_PROTECTION -> getString(R.string.passMethodWithoutProtection)
        }

    /**
     * Returns string value for a day of week
     */
    override fun getDayOfWeekString(dayOfWeek: DayOfWeek): String =
        when(dayOfWeek) {
            DayOfWeek.MONDAY -> getString(R.string.dayMonday)
            DayOfWeek.TUESDAY -> getString(R.string.dayTuesday)
            DayOfWeek.WEDNESDAY -> getString(R.string.dayWednesday)
            DayOfWeek.THURSDAY -> getString(R.string.dayThursday)
            DayOfWeek.FRIDAY -> getString(R.string.dayFriday)
            DayOfWeek.SATURDAY -> getString(R.string.daySaturday)
            DayOfWeek.SUNDAY -> getString(R.string.daySunday)
        }
}