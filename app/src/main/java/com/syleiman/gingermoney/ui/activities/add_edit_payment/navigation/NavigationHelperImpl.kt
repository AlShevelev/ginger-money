package com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation

import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseImpl
import javax.inject.Inject

class NavigationHelperImpl
@Inject
constructor() : NavigationHelperBaseImpl(R.id.addEditPaymentNavHostFragment), NavigationHelper {
    override fun setAddPaymentAsHome(activity: FragmentActivity)  =
        setHome(R.id.addPaymentFragment, R.navigation.activity_add_edit_payment,  activity, null)

//    override fun setEditAccountAsHome(activity: FragmentActivity, accountDbId: Long)  {
//        val bundle = Bundle()
//        bundle.putLong(NavigationArgs.DB_ID, accountDbId)
//
//        setHome(R.id.editAccountFragment, R.navigation.activity_add_edit_account, activity, bundle)
//    }
}