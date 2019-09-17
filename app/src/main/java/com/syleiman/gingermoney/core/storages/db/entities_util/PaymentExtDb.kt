package com.syleiman.gingermoney.core.storages.db.entities_util

import androidx.room.Embedded
import androidx.room.Relation
import com.syleiman.gingermoney.core.storages.db.entities.AccountDb
import com.syleiman.gingermoney.core.storages.db.entities.PaymentCategoryDb
import com.syleiman.gingermoney.core.storages.db.entities.PaymentDb

/** */
class PaymentExtDb {
    @Embedded
    lateinit var payment: PaymentDb

    @Relation(
        parentColumn = "account_id",
        entityColumn = "account_id",
        entity = AccountDb::class)
    lateinit var accounts: List<AccountDb>

    @Relation(
        parentColumn = "payment_category_id",
        entityColumn = "payment_category_id",
        entity = PaymentCategoryDb::class)
    lateinit var paymentCategories: List<PaymentCategoryDb>
}