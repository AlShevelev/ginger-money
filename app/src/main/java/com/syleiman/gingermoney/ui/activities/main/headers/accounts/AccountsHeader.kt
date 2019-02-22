package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.headers.FragmentHeaderBase
import kotlinx.android.synthetic.main.fragment_main_accounts_header.view.*
import javax.inject.Inject

/** Show current page as a set of dots */
class AccountsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FragmentHeaderBase(context, attrs, defStyleAttr, R.layout.fragment_main_accounts_header),
    AccountsHeaderFragmentInterface {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) = AccountsHeader(
            context
        ).setup(title, toolbar)
    }

    @Inject
    internal lateinit var headerLink: AccountsHeaderLinkInterface

    init {
        App.injections.get<MainActivityComponent>().inject(this)
        headerLink.attach(this)
    }

    /**
     *
     */
    override fun detachFromFragment() {
        headerLink.detachHeader()
    }

    /**
     *
     */
    override fun setAddButtonClickListener(listener: OnClickListener?) = addButton.setOnClickListener(listener)
}