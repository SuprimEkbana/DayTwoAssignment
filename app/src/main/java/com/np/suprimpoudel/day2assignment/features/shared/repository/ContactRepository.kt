package com.np.suprimpoudel.day2assignment.features.shared.repository

import com.np.suprimpoudel.day2assignment.database.AppDatabase
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails

class ContactRepository(private val contactAppDatabase: AppDatabase) {

    private val contactList = ArrayList<ContactDetails>()

    init {
        contactList.addAll(contactAppDatabase.getContactDao().getAllContacts())
    }

    fun getAllContactList(): ArrayList<ContactDetails> = contactList

    fun addContactDetail(contactDetails: ContactDetails) {
        contactAppDatabase.getContactDao().insert(contactDetails)
        contactList.add(contactDetails)
    }

    fun updateContactDetail(contactDetails: ContactDetails, position: Int) {
        contactAppDatabase.getContactDao().update(contactDetails)
        contactList[position].phoneNumber = contactDetails.phoneNumber
        contactList[position].name = contactDetails.name
        contactList[position].address = contactDetails.address
    }

    fun deleteContactDetail(contactDetails: ContactDetails, position: Int) {
        contactAppDatabase.getContactDao().delete(contactDetails)
        contactList.removeAt(position)
    }

    fun getSingleContactDetail(id: Int): ContactDetails {
        if (!contactList.isNullOrEmpty()) {
            for (userContact in contactList) {
                if (userContact.contactId == id) {
                    return userContact
                }
            }
        }
        return ContactDetails(name = "", address = "", phoneNumber = "")
    }
}