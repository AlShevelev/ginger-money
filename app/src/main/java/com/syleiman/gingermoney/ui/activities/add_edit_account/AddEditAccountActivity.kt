package com.syleiman.gingermoney.ui.activities.add_edit_account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ACCOUNT_ACTION
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ACCOUNT_DB_ID
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ADD
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.EDIT
import kotlinx.android.synthetic.main.activity_add_edit_account.*
import kotlinx.android.synthetic.main.activity_add_edit_account_header.*
import javax.inject.Inject

class AddEditAccountActivity : AppCompatActivity() {
    @Inject
    lateinit var navigation: NavigationHelperInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_account)

        App.injections.get<AddEditAccountActivityComponent>().inject(this)

        setSupportActionBar(addEditAccountToolbar)

        var showDeleteButton = false
        intent.extras
            ?.let { extras ->
                when(extras.getString(ACCOUNT_ACTION)) {
                    ADD -> navigation.setAddAccountAsHome(this)
                    EDIT -> {
                        navigation.setEditAccountAsHome(this, extras.getLong(ACCOUNT_DB_ID))
                        showDeleteButton = true
                    }
                }
            }


        AddEditAccountHeader.create(this, navigation.getTitle(this), addEditAccountToolbar, showDeleteButton)

        backButton.setOnClickListener { navigation.moveBack(this) }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigation.processBackAnimation(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            App.injections.release<AddEditAccountActivityComponent>()
        }
    }
}