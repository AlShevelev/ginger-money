package com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.view_model

import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase

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