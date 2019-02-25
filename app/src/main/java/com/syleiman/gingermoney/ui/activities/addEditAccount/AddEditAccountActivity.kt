package com.syleiman.gingermoney.ui.activities.addEditAccount

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.addEditAccount.navigation.NavigationHelperInterface
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

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_account)

        App.injections.get<AddEditAccountActivityComponent>().inject(this)

        setSupportActionBar(addEditAccountToolbar)

        val startAction = intent.extras!!.getString(ACCOUNT_ACTION)
        when(startAction) {
            ADD -> navigation.setAddAccountAsHome(this)
            EDIT -> navigation.setEditAccountAsHome(this)
        }

        AddEditAccountHeader.create(this, navigation.getTitle(this), addEditAccountToolbar)

        backButton.setOnClickListener { onBackPressed() }
    }

    /**
     *
     */
    override fun onBackPressed() {
        super.onBackPressed()
        navigation.processBackAnimation(this)
    }
}

//Left margin of the Back button is too large