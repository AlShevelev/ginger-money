package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase
import kotlinx.android.synthetic.main.header_fragment_main_accounts.view.*
import javax.inject.Inject

class AccountsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.header_fragment_main_accounts),
    AccountsHeaderFragment {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) =
            AccountsHeader(context).setup(title, toolbar)
    }

    @Inject
    internal lateinit var headerLink: AccountsHeaderLink

    init {
        App.injections.get<MainActivityComponent>().inject(this)
        headerLink.attachHeader(this)
    }

    override fun detachFromFragment() {
        headerLink.detachHeader()
    }

    override fun setAddButtonClickListener(listener: OnClickListener?) = addButton.setOnClickListener(listener)
}