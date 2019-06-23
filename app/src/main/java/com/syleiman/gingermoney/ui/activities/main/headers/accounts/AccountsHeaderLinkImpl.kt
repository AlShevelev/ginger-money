package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import android.view.View
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragmentHeader
import javax.inject.Inject

/**
 * Link between a header & a fragment
 */
class AccountsHeaderLinkImpl
@Inject
constructor() : AccountsHeaderLink {
    private var fragment: AccountsFragmentHeader? = null

    private var header: AccountsHeaderFragment? = null

    override fun attach(fragment: AccountsFragmentHeader) {
        this.fragment = fragment

        header?.also {
            it.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }
    }

    override fun attach(header: AccountsHeaderFragment) {
        this.header = header

        fragment?.also { fragment ->
            header.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }

    }

    override fun detachFragment() {
        fragment = null

        header?.also {
            it.setAddButtonClickListener(null)
        }
    }

    override fun detachHeader() {
        header?.also {
            it.setAddButtonClickListener(null)
        }

        header = null
    }
}