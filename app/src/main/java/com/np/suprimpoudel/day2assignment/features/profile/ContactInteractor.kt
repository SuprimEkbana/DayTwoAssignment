package com.np.suprimpoudel.day2assignment.features.profile

import com.np.suprimpoudel.day2assignment.database.AppDatabase
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails
import com.np.suprimpoudel.day2assignment.features.shared.repository.ContactRepository

class ContactInteractor(appDatabase: AppDatabase) {
    private val contactRepository = ContactRepository(appDatabase)

    fun getAllContactList() = contactRepository.getAllContactList()

    fun addContactDetail(contactDetails: ContactDetails) =
        contactRepository.addContactDetail(contactDetails)

    fun updateContactDetail(contactDetails: ContactDetails, position: Int) =
        contactRepository.updateContactDetail(contactDetails, position)

    fun deleteContactDetail(contactDetails: ContactDetails, position: Int) =
        contactRepository.deleteContactDetail(contactDetails, position)

    fun getSingleContactDetail(appDatabase: AppDatabase, id: Int) =
        contactRepository.getSingleContactDetail(id)
}