package com.syleiman.gingermoney.core.utils.app_resources

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.*
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.date_time.DateTimeFormat
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.dto.enums.Color
import org.threeten.bp.DayOfWeek
import java.lang.UnsupportedOperationException
import java.text.MessageFormat
import javax.inject.Inject

class AppResourcesProviderImpl
@Inject
constructor(
    private val appContext: Context
) : AppResourcesProvider {

    override fun getLocale(): String = getString(R.string.locale)

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

    override fun getInt(@IntegerRes resId: Int): Int = appContext.resources.getInteger(resId)

    @Suppress("DEPRECATION")
    @ColorInt
    override fun getColor(@ColorRes resId: Int): Int =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            appContext.resources.getColor(resId, null)
        } else {
            appContext.resources.getColor(resId)
        }

    @ColorInt
    override fun getColor(color: Color): Int =
        when(color) {
            Color.BLACK -> getColor(R.color.black)
            Color.WHITE -> getColor(R.color.white)
            Color.RED -> getColor(R.color.red)
            Color.PINK -> getColor(R.color.pink)
            Color.PURPLE -> getColor(R.color.purple)
            Color.DEEP_PURPLE -> getColor(R.color.deepPurple)
            Color.INDIGO -> getColor(R.color.indigo)
            Color.BLUE -> getColor(R.color.blue)
            Color.LIGHT_BLUE -> getColor(R.color.lightBlue)
            Color.CYAN -> getColor(R.color.cyan)
            Color.TEAL -> getColor(R.color.teal)
            Color.GREEN -> getColor(R.color.green)
            Color.LIGHT_GREEN -> getColor(R.color.lightGreen)
            Color.LIME -> getColor(R.color.lime)
            Color.YELLOW -> getColor(R.color.yellow)
            Color.AMBER -> getColor(R.color.amber)
            Color.ORANGE -> getColor(R.color.orange)
            Color.DEEP_ORANGE -> getColor(R.color.deepOrange)
        }

    override fun getBool(@BoolRes boolResId: Int): Boolean = appContext.resources.getBoolean(boolResId)

    /**
     * Get dimension value in pixels
     */
    override fun getDimension(@DimenRes resId: Int): Float = appContext.resources.getDimension(resId)

    /** Get drawable resource */
    override fun getDrawable(@DrawableRes resId: Int) : Drawable = appContext.resources.getDrawable(resId, null)

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

    /**
     * Returns string value for an account group
     */
    override fun getAccountGroupString(group: AccountGroup): String =
        when(group) {
            AccountGroup.CASH -> getString(R.string.paymentCash)
            AccountGroup.CARDS -> getString(R.string.paymentCards)
            AccountGroup.CREDIT_CARDS -> getString(R.string.paymentCreditCards)
            AccountGroup.DEBIT_CARDS -> getString(R.string.paymentDebitCards)
            AccountGroup.ACCOUNTS -> getString(R.string.paymentAccounts)
            AccountGroup.DEPOSITS -> getString(R.string.paymentDeposits)
            AccountGroup.SAVINGS -> getString(R.string.paymentSavings)
            AccountGroup.INVESTMENTS -> getString(R.string.paymentInvestments)
            AccountGroup.SHARES -> getString(R.string.paymentShares)
            AccountGroup.BONDS -> getString(R.string.paymentBonds)
            AccountGroup.OTHER -> getString(R.string.paymentOther)
        }

    override fun getDateTimeFormat(format: DateTimeFormat): String =
        when(format) {
            DateTimeFormat.MONTH_AND_YEAR -> getString(R.string.dateFormatMonthAndYear)
        }
}