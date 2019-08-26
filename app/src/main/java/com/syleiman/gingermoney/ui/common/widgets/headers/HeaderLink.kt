package com.syleiman.gingermoney.ui.common.widgets.headers

/**
 * Link between a header & a fragment
 */
interface HeaderLink<TF, TH> {
    val header: TH?

    fun attachFragment(fragment: TF)

    fun attachHeader(header: TH)

    fun detachFragment()

    fun detachHeader()
}