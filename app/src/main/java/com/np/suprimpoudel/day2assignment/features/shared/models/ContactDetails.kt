package com.np.suprimpoudel.day2assignment.features.shared.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.np.suprimpoudel.day2assignment.utils.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_NAME)
data class ContactDetails(
    @PrimaryKey(autoGenerate = true)
    var contactId: Int = 0,
    @ColumnInfo(name = DatabaseConstants.KEY_NAME)
    var name: String? = null,
    @ColumnInfo(name = DatabaseConstants.KEY_ADDRESS)
    var address: String? = null,
    @ColumnInfo(name = DatabaseConstants.KEY_PHONE_NUMBER)
    var phoneNumber: String? = null,
)