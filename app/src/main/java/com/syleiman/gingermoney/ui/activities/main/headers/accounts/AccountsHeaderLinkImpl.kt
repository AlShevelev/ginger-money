package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import android.view.View
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragmentHeader
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderLinkBaseImpl
import javax.inject.Inject

/**
 * Link between a header & a fragment
 */
class AccountsHeaderLinkImpl
@Inject
constructor() : HeaderLinkBaseImpl<AccountsFragmentHeader, AccountsHeaderFragment>(), AccountsHeaderLink {

    override fun attachFragment(fragment: AccountsFragmentHeader) {
        super.attachFragment(fragment)

        header?.also {
            it.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }
    }

    override fun attachHeader(header: AccountsHeaderFragment) {
        super.attachHeader(header)

        fragment?.also { fragment ->
            header.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }

    }

    override fun detachFragment() {
        super.detachFragment()

        header?.also {
            it.setAddButtonClickListener(null)
        }
    }

    override fun detachHeader() {
        header?.also {
            it.setAddButtonClickListener(null)
        }

        super.detachHeader()
    }
}