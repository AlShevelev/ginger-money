package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model

import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface EditAccountModelInterface: AddAccountModelInterface {
    suspend fun getAccount(): ModelCallResult<out Account>

    suspend fun canUpdateCurrency(): ModelCallResult<out Boolean>
}