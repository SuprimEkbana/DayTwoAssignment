package com.np.suprimpoudel.day2assignment.features.profile

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.np.suprimpoudel.day2assignment.database.AppDatabase
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails

class ContactPresenter(private val appDatabase: AppDatabase) : MvpBasePresenter<ContactView>() {
    private val contactInteractor = ContactInteractor(appDatabase)

    fun showContactList() {
        ifViewAttached { view ->
            view.setDataIntoRecyclerView(contactInteractor.getAllContactList())
        }
    }

    fun removeContact(position: Int) {
        ifViewAttached { view ->
            val contactDetails = appDatabase.getContactDao().getAllContacts()
            val contact =
                contactInteractor.getSingleContactDetail(
                    appDatabase,
                    contactDetails[position].contactId
                )
            contactInteractor.deleteContactDetail(contact, position)
            view.refreshScreen()
            view.showMessage("Deleted contact info")
        }
    }

    fun addContactList(contactDetails: ContactDetails) {
        ifViewAttached { view ->
            contactInteractor.addContactDetail(contactDetails)
            view.refreshScreen()
            view.showMessage("Added contact info")
        }
    }

    fun updateContactList(contactDetails: ContactDetails, position: Int) {
        ifViewAttached { view ->
            contactInteractor.updateContactDetail(contactDetails, position)
            view.refreshScreen()
            view.showMessage("Updated Successfully")
        }
    }

    fun getSingleContactDetail(position: Int) {
        ifViewAttached { view ->
            val contactDetails = appDatabase.getContactDao().getAllContacts()
            val contact =
                contactInteractor.getSingleContactDetail(
                    appDatabase,
                    contactDetails[position].contactId
                )
            view.setDataIntoDialog(contact)
        }
    }
}