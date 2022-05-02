package com.np.suprimpoudel.day2assignment.features.profile

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails

interface ContactView : MvpView {
    fun setDataIntoRecyclerView(contactDetails: ArrayList<ContactDetails>)
    fun showMessage(message: String)
    fun setDataIntoDialog(contactDetail: ContactDetails)
    fun refreshScreen()
}