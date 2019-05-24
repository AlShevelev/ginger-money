package com.syleiman.gingermoney.ui.activities.main.headers.settings

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.BuildConfig
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.common.widgets.HeaderBase
import kotlinx.android.synthetic.main.fragment_main_settings_header.view.*
import javax.inject.Inject

class SettingsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.fragment_main_settings_header) {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) = SettingsHeader(context).setup(title, toolbar)
    }

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    init {
        App.injections.get<MainActivityComponent>().inject(this)

        appVersion.text = resourcesProvider.getFormattedString(
            R.string.appVersion,
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE)
    }
}