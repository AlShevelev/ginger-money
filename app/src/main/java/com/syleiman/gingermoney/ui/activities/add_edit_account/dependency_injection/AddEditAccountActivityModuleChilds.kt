package com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dependency_injection.AddAccountFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection.EditAccountFragmentComponent
import dagger.Module

@Module(subcomponents = [AddAccountFragmentComponent::class, EditAccountFragmentComponent::class])
class AddEditAccountActivityModuleChilds