package com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation

import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase

interface NavigationHelper: NavigationHelperBase {
    fun setAddPaymentAsHome(activity: FragmentActivity)
//
//    fun setEditPaymentAsHome(activity: FragmentActivity, paymentDbId: Long)
}