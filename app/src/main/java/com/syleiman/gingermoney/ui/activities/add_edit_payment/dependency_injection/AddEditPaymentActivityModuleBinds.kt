package com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories.ListCategoriesHeaderLink
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories.ListCategoriesHeaderLinkImpl
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AddEditPaymentActivityModuleBinds {
    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelperImpl): NavigationHelper

    @Binds
    @ActivityScope
    abstract fun provideListCategoriesHeaderLink(link: ListCategoriesHeaderLinkImpl): ListCategoriesHeaderLink
}