package com.syleiman.gingermoney.ui.activities.main.headers

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import javax.inject.Inject

/** Show current page as a set of dots */
class AccountsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FragmentHeaderBase(context, attrs, defStyleAttr, R.layout.fragment_main_accounts_header) {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) = AccountsHeader(context).setup(title, toolbar)
    }

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    init {
        App.injections.get<MainActivityComponent>().inject(this)
    }
}