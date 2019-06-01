package com.syleiman.gingermoney.ui.activities.add_edit_account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.navigation.NavigationHelperInterface
import kotlinx.android.synthetic.main.activity_add_edit_account.*
import kotlinx.android.synthetic.main.activity_add_edit_account_header.*
import javax.inject.Inject

class AddEditAccountActivity : AppCompatActivity() {
    companion object {
        const val ACCOUNT_ACTION = "ACCOUNT_ACTION"

        const val ADD = "ADD"
        const val EDIT = "EDIT"
    }

    @Inject
    lateinit var navigation: NavigationHelperInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_account)

        App.injections.get<AddEditAccountActivityComponent>().inject(this)

        setSupportActionBar(addEditAccountToolbar)

        when(intent.extras!!.getString(ACCOUNT_ACTION)) {
            ADD -> navigation.setAddAccountAsHome(this)
            EDIT -> navigation.setEditAccountAsHome(this)
        }

        AddEditAccountHeader.create(this, navigation.getTitle(this), addEditAccountToolbar)

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