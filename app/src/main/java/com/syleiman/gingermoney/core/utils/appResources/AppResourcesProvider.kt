package com.syleiman.gingermoney.core.utils.appResources

import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.syleiman.gingermoney.R
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
     * Get string and format it
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
     * Get metadata value as string
     */
    override fun getMetadataValue(key: String): Any? {
        try {
            return appContext
                .packageManager
                .getApplicationInfo(appContext.packageName, PackageManager.GET_META_DATA)
                .metaData
                .get(key)
        }
        catch (ex: PackageManager.NameNotFoundException) {
            ex.printStackTrace()
        }

        return null
    }
}