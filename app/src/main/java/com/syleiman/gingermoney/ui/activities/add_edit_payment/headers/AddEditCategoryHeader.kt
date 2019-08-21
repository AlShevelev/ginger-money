package com.syleiman.gingermoney.ui.activities.add_edit_payment.headers

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.extension.getParentActivity
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase
import kotlinx.android.synthetic.main.header_title_and_back.view.*
import javax.inject.Inject

class AddEditCategoryHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.header_title_and_back) {

    @Inject
    lateinit var navigation: NavigationHelper

    init {
        App.injections.get<AddEditPaymentActivityComponent>().inject(this)
    }

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) =
            AddEditCategoryHeader(context).setup(title, toolbar)
    }

    override fun setup(title: CharSequence?, toolbar: Toolbar) {
        super.setup(title, toolbar)
        backButton.setOnClickListener { navigation.moveBack(getParentActivity() as FragmentActivity) }
    }
}