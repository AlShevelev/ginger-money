package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import android.view.View
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragmentHeaderInterface
import javax.inject.Inject

/**
 * Link between a header & a fragment
 */
class AccountsHeaderLink
@Inject
constructor() : AccountsHeaderLinkInterface {
    private var fragment: AccountsFragmentHeaderInterface? = null

    private var header: AccountsHeaderFragmentInterface? = null

    /**
     *
     */
    override fun attach(fragment: AccountsFragmentHeaderInterface) {
        this.fragment = fragment

        header?.also {
            it.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }
    }

    /**
     *
     */
    override fun attach(header: AccountsHeaderFragmentInterface) {
        this.header = header

        fragment?.also { fragment ->
            header.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }

    }

    /**
     *
     */
    override fun detachFragment() {
        fragment = null

        header?.also {
            it.setAddButtonClickListener(null)
        }
    }

    /**
     *
     */
    override fun detachHeader() {
        header?.also {
            it.setAddButtonClickListener(null)
        }

        header = null
    }
}