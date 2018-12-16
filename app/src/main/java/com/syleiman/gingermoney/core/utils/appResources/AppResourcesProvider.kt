package com.syleiman.gingermoney.core.utils.appResources

import android.content.Context
import android.content.pm.PackageManager
import com.syleiman.gingermoney.R
import javax.inject.Inject

class AppResourcesProvider
@Inject
constructor(
    private val appContext: Context
) : AppResourcesProviderInterface {

    /** */
    override fun getLocale(): String = getString(R.string.locale)

    /** */
    override fun getString(resId: Int): String = appContext.getString(resId)

    /** Get metadata value as string */
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