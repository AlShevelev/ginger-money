package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model

import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModel
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface EditAccountModel: AddAccountModel {
    suspend fun getAccount(): ModelCallResult<out Account>

    suspend fun canUpdateCurrency(): ModelCallResult<out Boolean>
}