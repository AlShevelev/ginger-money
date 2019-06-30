package com.syleiman.gingermoney.ui.activities.add_edit_payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ACTION
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ADD
import kotlinx.android.synthetic.main.activity_add_edit_payment.*
import kotlinx.android.synthetic.main.activity_add_edit_payment_header.*
import javax.inject.Inject

class AddEditPaymentActivity : AppCompatActivity() {
    @Inject
    lateinit var navigation: NavigationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_payment)

        App.injections.get<AddEditPaymentActivityComponent>().inject(this)

        setSupportActionBar(addEditPaymentToolbar)

        var showAddButton = false
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


        AddEditPaymentHeader.create(this, navigation.getTitle(this), addEditPaymentToolbar, showAddButton)

        backButton.setOnClickListener { navigation.moveBack(this) }
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
}