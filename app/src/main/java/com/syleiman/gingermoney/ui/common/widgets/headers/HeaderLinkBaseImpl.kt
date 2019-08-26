package com.syleiman.gingermoney.ui.common.widgets.headers

import androidx.annotation.CallSuper

abstract class HeaderLinkBaseImpl<TF, TH>: HeaderLink<TF, TH> {
    protected var fragment: TF? = null

    override var header: TH? = null
    protected set

    @CallSuper
    override fun attachFragment(fragment: TF) {
        this.fragment = fragment
    }

    @CallSuper
    override fun attachHeader(header: TH) {
        this.header = header
    }

    @CallSuper
    override fun detachFragment() {
        fragment = null
    }

    @CallSuper
    override fun detachHeader() {
        header = null
    }
}