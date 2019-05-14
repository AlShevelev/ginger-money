package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model

import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import javax.inject.Inject

/**
 *
 */
class AddAccountViewModel : ViewModelBase<AddAccountModelInterface>() {
    /**
     *
     */
    init {
        App.injections.get<AddEditAccountActivityComponent>().inject(this)


    }
}