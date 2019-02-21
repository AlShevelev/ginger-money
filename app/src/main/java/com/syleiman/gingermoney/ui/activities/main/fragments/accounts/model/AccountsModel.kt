package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 */
class AccountsModel
@Inject
constructor(
    private val db: DbStorageFacadeInterface
) : ModelBase(),
    AccountsModelInterface {

    /**
     *
     */
    override fun getAllAccounts(resultCallback: (List<Account>?, DisplayingError?) -> Unit) {
        launch {
            val accounts = try {
                withContext(Dispatchers.IO) {
                    db.getAccounts()
                }
            }
            catch(ex: Exception) {
                ex.printStackTrace()
                null
            }

            if(isActive) {
                resultCallback(accounts, if(accounts == null) GeneralError() else null)
            }
        }
    }
}