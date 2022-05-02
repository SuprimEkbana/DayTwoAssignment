package com.np.suprimpoudel.day2assignment.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails
import com.np.suprimpoudel.day2assignment.utils.constants.DatabaseConstants

@Dao
interface ContactDao {
    @Insert()
    fun insert(contact: ContactDetails)

    @Update(onConflict = REPLACE)
    fun update(contact: ContactDetails)

    @Delete()
    fun delete(contact: ContactDetails)

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_NAME}")
    fun getAllContacts(): List<ContactDetails>
}