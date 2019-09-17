package com.syleiman.gingermoney.ui.activities.main.headers.payments

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.date_time.DateTimeFormat
import com.syleiman.gingermoney.core.global_entities.date_time.format
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase
import kotlinx.android.synthetic.main.header_fragment_main_payments.view.*
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class PaymentsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.header_fragment_main_payments),
    PaymentsHeaderFragment {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) = PaymentsHeader(
            context
        ).setup(title, toolbar)
    }

    @Inject
    internal lateinit var headerLink: PaymentsHeaderLink

    @Inject
    internal lateinit var appResourceProvider: AppResourcesProvider

    init {
        App.injections.get<MainActivityComponent>().inject(this)
        headerLink.attachHeader(this)
    }

    override fun detachFromFragment() {
        headerLink.detachHeader()
    }

    override fun setOnNextMonthButtonClickListener(listener: OnClickListener?) = nextButton.setOnClickListener(listener)

    override fun setOnPriorMonthButtonClickListener(listener: OnClickListener?) = priorButton.setOnClickListener(listener)

    override fun setDateToDisplay(date: ZonedDateTime, isLastPeriod: Boolean) {
        nextButton.isEnabled = !isLastPeriod

        monthAndYear.text = date.format(DateTimeFormat.MONTH_AND_YEAR, appResourceProvider)
    }
}