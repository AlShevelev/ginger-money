package com.syleiman.gingermoney.ui.activities.add_edit_payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.AddEditCategoryHeader
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.AddPaymentHeader
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.HeaderTags
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories.ListCategoriesHeader
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ACTION
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ADD
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase
import kotlinx.android.synthetic.main.activity_add_edit_payment.*
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class AddEditPaymentActivity : AppCompatActivity() {
    @Inject
    lateinit var navigation: NavigationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_payment)

        App.injections.get<AddEditPaymentActivityComponent>().inject(this)

        setSupportActionBar(addEditPaymentToolbar)

        navigation.setOnDestinationChangedListener(this) { title, tag -> updateHeader(title, tag) }

        intent.extras
            ?.let { extras ->
                when(extras.getString(ACTION)) {
                    ADD -> navigation.setAddPaymentAsHome(this)
//                    EDIT -> {
//                        navigation.setEditAccountAsHome(this, extras.getLong(ACCOUNT_DB_ID))
//                        showDeleteButton = true
//                    }
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigation.processBackAnimation(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            App.injections.release<AddEditPaymentActivityComponent>()
        }
    }

    private fun updateHeader(title: CharSequence?, tag: String) {
        // Remove an old header
        addEditPaymentToolbar.findViewWithTag<ConstraintLayout>(HeaderBase.CURRENT_HEADER_TAG)
            ?.let {
                (it as HeaderBase).detachFromFragment()
                addEditPaymentToolbar.removeView(it)
            }

        // And add a new one
        when(tag) {
            HeaderTags.ADD_PAYMENT -> AddPaymentHeader.create(this, title, addEditPaymentToolbar)
            HeaderTags.LIST_CATEGORIES -> ListCategoriesHeader.create(this, title, addEditPaymentToolbar)
            HeaderTags.ADD_CATEGORY -> AddEditCategoryHeader.create(this, title, addEditPaymentToolbar)
            HeaderTags.EDIT_CATEGORY -> AddEditCategoryHeader.create(this, title, addEditPaymentToolbar)
            else -> throw UnsupportedOperationException("This tag is not supported")
        }
    }
}