package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragmentHeaderInterface

/**
 * Link between a header & a fragment
 */
interface AccountsHeaderLinkInterface {

    /**
     *
     */
    fun attach(fragment: AccountsFragmentHeaderInterface)

    /**
     *
     */
    fun attach(header: AccountsHeaderFragmentInterface)

    /**
     *
     */
    fun detachFragment()

    /**
     *
     */
    fun detachHeader()
}