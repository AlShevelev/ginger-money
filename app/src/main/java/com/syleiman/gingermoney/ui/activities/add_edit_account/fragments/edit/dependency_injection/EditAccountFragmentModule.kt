package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.Clarification
import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class EditAccountFragmentModule(private val accountDbId: Long) {
    @Provides
    @FragmentScope
    @Named(Clarification.ACCOUNT_DB_ID)
    internal fun provideAccountDbId(): Long = accountDbId
}