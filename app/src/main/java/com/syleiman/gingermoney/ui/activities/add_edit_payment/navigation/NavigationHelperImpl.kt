package com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view.AddPaymentFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.ListCategoriesFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.HeaderTags
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseImpl
import javax.inject.Inject

class NavigationHelperImpl
@Inject
constructor(
    private val appResourcesProvider: AppResourcesProvider
) : NavigationHelperBaseImpl(R.id.addEditPaymentNavHostFragment), NavigationHelper {

    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    /**
     * @param listener Listener to set. First parameter is a label; second is a destination point tag
     */
    override fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (CharSequence?, String) -> Unit) {
        val controller = getNavigationController(activity)

        onDestinationChangedListener?.let { controller.removeOnDestinationChangedListener(it) }

        NavController
            .OnDestinationChangedListener { _, destination, args ->
                when(destination.id) {
                    R.id.addPaymentFragment -> listener(destination.label, HeaderTags.ADD_PAYMENT)
                    R.id.addEditCategoryFragment -> {
                        if(args!= null && args.containsKey(NavigationArgs.DB_ID)) {
                            listener(appResourcesProvider.getString(R.string.addEditPaymentAddCategoryTitle), HeaderTags.EDIT_CATEGORY)
                        } else {
                            listener(appResourcesProvider.getString(R.string.addEditPaymentEditCategoryTitle), HeaderTags.ADD_CATEGORY)
                        }
                    }
                    R.id.listCategoryFragment -> listener(destination.label, HeaderTags.LIST_CATEGORIES)
                    else -> throw UnsupportedOperationException("Unknown Id: ${destination.id}")
                }
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

    override fun moveToAddCategory(fragment: ListCategoriesFragment) {
        getNavigationController(fragment).navigate(R.id.action_listCategoryFragment_to_addEditCategoryFragment)
    }

    override fun moveToEditCategory(fragment: ListCategoriesFragment, categoryDbId: Long) {
        val bundle = Bundle()
        bundle.putLong(NavigationArgs.DB_ID, categoryDbId)

        getNavigationController(fragment).navigate(R.id.action_listCategoryFragment_to_addEditCategoryFragment, bundle)
    }

//    override fun setEditAccountAsHome(activity: FragmentActivity, accountDbId: Long)  {
//        val bundle = Bundle()
//        bundle.putLong(NavigationArgs.DB_ID, accountDbId)
//
//        setHome(R.id.editAccountFragment, R.navigation.activity_add_edit_account, activity, bundle)
//    }
}