package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.syleiman.gingermoney.core.globalEntities.dateTime.ZonedDateTimeSplit
import com.syleiman.gingermoney.core.globalEntities.money.Money

/**
 *
 */
@Entity(
    tableName = "expense",
    foreignKeys = [
        ForeignKey(
            entity = AccountDb::class,
            parentColumns = ["account_id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = ExpenseCategoryDb::class,
            parentColumns = ["expense_category_id"],
            childColumns = ["expense_category_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ])
data class ExpenseDb (
    @PrimaryKey
    @ColumnInfo(name = "expense_id")
    var id: Long,

    @ColumnInfo(name = "account_id", index = true)
    var accountId: Long,

    @ColumnInfo(name = "expense_category_id", index = true)
    var expenseCategoryId: Long,

    @ColumnInfo(name = "amount", typeAffinity = ColumnInfo.BLOB)
    val amount: Money,

    @ColumnInfo(name = "memo")
    val memo: String?,

    @ColumnInfo(name = "memo_image_file_name")
    val memoImageFileName: String?,

    @ColumnInfo(name = "create_at", typeAffinity = ColumnInfo.BLOB)
    val createAt: ZonedDateTimeSplit,

    @ColumnInfo(name = "create_at_estimate", index = true)
    val createAtEstimate: Int
)