package com.syleiman.gingermoney.ui.activities.main.headers.payments

import android.view.View
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.PaymentsFragmentHeader
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderLinkBaseImpl
import javax.inject.Inject

/**
 * Link between a header & a fragment
 */
class PaymentsHeaderLinkImpl
@Inject
constructor() : HeaderLinkBaseImpl<PaymentsFragmentHeader, PaymentsHeaderFragment>(), PaymentsHeaderLink {

    override fun attachFragment(fragment: PaymentsFragmentHeader) {
        super.attachFragment(fragment)

        header?.also {
            it.setOnNextMonthButtonClickListener(View.OnClickListener { fragment.onNextMonthButtonClick() })
            it.setOnPriorMonthButtonClickListener(View.OnClickListener { fragment.onPriorMonthButtonClick() })
        }
    }

    override fun attachHeader(header: PaymentsHeaderFragment) {
        super.attachHeader(header)

        fragment?.also { fragment ->
            header.setOnNextMonthButtonClickListener(View.OnClickListener { fragment.onNextMonthButtonClick() })
            header.setOnPriorMonthButtonClickListener(View.OnClickListener { fragment.onPriorMonthButtonClick() })
        }

    }

    override fun detachFragment() {
        super.detachFragment()

        header?.also {
            it.setOnNextMonthButtonClickListener(null)
            it.setOnPriorMonthButtonClickListener(null)
        }
    }

    override fun detachHeader() {
        header?.also {
            it.setOnNextMonthButtonClickListener(null)
            it.setOnPriorMonthButtonClickListener(null)
        }

        super.detachHeader()
    }
}