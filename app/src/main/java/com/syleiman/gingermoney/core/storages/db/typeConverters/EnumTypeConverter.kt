package com.syleiman.gingermoney.core.storages.db.typeConverters

import androidx.room.TypeConverter
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color

/**
 *
 */
class EnumTypeConverter {
    /**
     *
     */
    @TypeConverter
    fun fromAccountGroupToDb(sourceData: AccountGroup?): Byte? = sourceData?.value

    /**
     *
     */
    @TypeConverter
    fun fromDbToAccountGroup(sourceData: Byte?): AccountGroup? = sourceData?.let { AccountGroup.from(it) }

    /**
     *
     */
    @TypeConverter
    fun fromColorToDb(sourceData: Color?): Byte? = sourceData?.value

    /**
     *
     */
    @TypeConverter
    fun fromDbToColor(sourceData: Byte?): Color? = sourceData?.let { Color.from(it) }
}