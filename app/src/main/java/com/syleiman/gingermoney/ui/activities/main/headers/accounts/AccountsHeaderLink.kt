package com.syleiman.gingermoney.ui.activities.main.headers.accounts

import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragmentHeader

/**
 * Link between a header & a fragment
 */
interface AccountsHeaderLink {

    fun attach(fragment: AccountsFragmentHeader)

    fun attach(header: AccountsHeaderFragment)

    fun detachFragment()

    fun detachHeader()
}