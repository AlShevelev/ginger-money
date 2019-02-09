package com.syleiman.gingermoney.ui.activities.main.fragments.settings.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.BuildConfig
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import kotlinx.android.synthetic.main.fragment_main_settings_header.view.*
import javax.inject.Inject

/** Show current page as a set of dots */
class SettingsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) {
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            val header = SettingsHeader(context)
            header.layoutParams = params
            header.setTitle(title)
            header.tag = HeaderTags.CURRENT_HEADER

            toolbar.addView(header)
        }
    }

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.fragment_main_settings_header, this)

        App.injections.get<MainActivityComponent>().inject(this)

        appVersion.text = resourcesProvider.getFormattedString(
            R.string.appVersion,
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE)
    }

    /**
     *
     */
    private fun setTitle(title: CharSequence?) {
        headerTitle.text = title
    }
}