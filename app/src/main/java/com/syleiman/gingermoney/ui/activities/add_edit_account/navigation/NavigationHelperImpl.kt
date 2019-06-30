package com.syleiman.gingermoney.ui.activities.add_edit_account.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseImpl
import javax.inject.Inject

class NavigationHelperImpl
@Inject
constructor() : NavigationHelperBaseImpl(R.id.addEditAccountNavHostFragment), NavigationHelper {

    override fun setAddAccountAsHome(activity: FragmentActivity) =
        setHome(R.id.addAccountFragment, R.navigation.activity_add_edit_account,  activity, null)

    override fun setEditAccountAsHome(activity: FragmentActivity, accountDbId: Long)  {
        val bundle = Bundle()
        bundle.putLong(NavigationArgs.DB_ID, accountDbId)

        setHome(R.id.editAccountFragment, R.navigation.activity_add_edit_account, activity, bundle)
    }
}