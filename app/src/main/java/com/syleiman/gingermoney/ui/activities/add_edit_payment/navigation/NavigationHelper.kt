package com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation

import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view.AddPaymentFragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase

interface NavigationHelper: NavigationHelperBase {
    /**
     * @param listener Listener to set. First parameter is a label; second is a destination point tag
     */
    fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (CharSequence?, String) -> Unit)

    fun setAddPaymentAsHome(activity: FragmentActivity)

    fun moveToListOfCategories(fragment: AddPaymentFragment)

//
//    fun setEditPaymentAsHome(activity: FragmentActivity, paymentDbId: Long)
}