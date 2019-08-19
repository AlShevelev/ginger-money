package com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view.AddPaymentFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.HeaderTags
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseImpl
import javax.inject.Inject

class NavigationHelperImpl
@Inject
constructor() : NavigationHelperBaseImpl(R.id.addEditPaymentNavHostFragment), NavigationHelper {
    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    /**
     * @param listener Listener to set. First parameter is a label; second is a destination point tag
     */
    override fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (CharSequence?, String) -> Unit) {
        val controller = getNavigationController(activity)

        onDestinationChangedListener?.let { controller.removeOnDestinationChangedListener(it) }

        NavController
            .OnDestinationChangedListener { _, destination, _ ->
                val tag = when(destination.id) {
                    R.id.addPaymentFragment -> HeaderTags.ADD_PAYMENT
                    R.id.addCategoryFragment -> HeaderTags.ADD_CATEGORY
                    R.id.listCategoryFragment -> HeaderTags.LIST_CATEGORIES
                    else -> throw UnsupportedOperationException("Unknown Id: ${destination.id}")
                }

                listener(destination.label, tag)
            }
            .apply {
                onDestinationChangedListener = this
                getNavigationController(activity).addOnDestinationChangedListener(this)
            }
    }

    override fun setAddPaymentAsHome(activity: FragmentActivity)  =
        setHome(R.id.addPaymentFragment, R.navigation.activity_add_edit_payment,  activity, null)

    override fun moveToListOfCategories(fragment: AddPaymentFragment) {
        getNavigationController(fragment).navigate(R.id.action_addPaymentFragment_to_listCategoryFragment)
    }

//    override fun setEditAccountAsHome(activity: FragmentActivity, accountDbId: Long)  {
//        val bundle = Bundle()
//        bundle.putLong(NavigationArgs.DB_ID, accountDbId)
//
//        setHome(R.id.editAccountFragment, R.navigation.activity_add_edit_account, activity, bundle)
//    }
}