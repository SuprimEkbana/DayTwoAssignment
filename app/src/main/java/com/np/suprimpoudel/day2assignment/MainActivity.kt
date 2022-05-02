package com.np.suprimpoudel.day2assignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.np.suprimpoudel.day2assignment.database.AppDatabase
import com.np.suprimpoudel.day2assignment.databinding.ActivityMainBinding
import com.np.suprimpoudel.day2assignment.databinding.DialogEditAddContactDetailBinding
import com.np.suprimpoudel.day2assignment.features.interfaces.ContactListTileOnClick
import com.np.suprimpoudel.day2assignment.features.profile.ContactPresenter
import com.np.suprimpoudel.day2assignment.features.profile.ContactView
import com.np.suprimpoudel.day2assignment.features.shared.adapters.ContactListAdapter
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails
import com.np.suprimpoudel.day2assignment.utils.constants.AppConstants

class MainActivity : MvpActivity<ContactView, ContactPresenter>(), ContactView,
    ContactListTileOnClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogEditAddContactDetailBinding

    private var contactListAdapter: ContactListAdapter? = null

    private lateinit var alertDialog: AlertDialog
    private lateinit var nameInput: TextInputEditText
    private lateinit var phoneInput: TextInputEditText
    private lateinit var addressInput: TextInputEditText

    private var cId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding for Main Activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        presenter.showContactList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addIcon -> showCustomDialog(AppConstants.ADD_ACTION, -1)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun createPresenter(): ContactPresenter = ContactPresenter(getDBReference())

    //ContactListTileOnClick Implementation
    override fun onDeleteIconClicked(position: Int) {
        presenter.removeContact(position)
    }

    override fun onContactListTileClicked(position: Int) {
        showCustomDialog(AppConstants.UPDATE_ACTION, position)
    }

    //MVP View Overridden Methods
    override fun setDataIntoRecyclerView(contactDetails: ArrayList<ContactDetails>) {
        contactListAdapter = ContactListAdapter(contactDetails, this)
        binding.recyclerContactList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerContactList.adapter = contactListAdapter
        binding.recyclerContactList.setHasFixedSize(true)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setDataIntoDialog(contactDetail: ContactDetails) {
        cId = contactDetail.contactId
        nameInput.setText(contactDetail.name)
        addressInput.setText(contactDetail.address)
        phoneInput.setText(contactDetail.phoneNumber)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun refreshScreen() {
        contactListAdapter?.notifyDataSetChanged()
    }

    //Custom Dialog
    private fun showCustomDialog(action: String, position: Int) {
        val builder = AlertDialog.Builder(this)
        val view = View.inflate(this, R.layout.dialog_edit_add_contact_detail, null)
        dialogBinding = DialogEditAddContactDetailBinding.bind(view)

        nameInput = dialogBinding.edtContactName
        addressInput = dialogBinding.edtContactAddress
        phoneInput = dialogBinding.edtContactPhone

        showDialogBox(builder, view, action, position)

        initListenerForDialog(action, position)
    }

    private fun showDialogBox(
        builder: AlertDialog.Builder,
        view: View,
        action: String,
        position: Int
    ) {
        builder.setView(view)
        alertDialog = builder.create()
        alertDialog.window?.setDimAmount(0.8f)
        alertDialog.setCancelable(true)
        alertDialog.show()
        alertDialog.window?.setGravity(Gravity.CENTER)

        if (action == AppConstants.UPDATE_ACTION) {
            dialogBinding.btnAddEdit.text = AppConstants.UPDATE_ACTION
            presenter.getSingleContactDetail(position)
        } else {
            dialogBinding.btnAddEdit.text = AppConstants.ADD_ACTION
        }
    }

    private fun initListenerForDialog(action: String, position: Int) {
        dialogBinding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        dialogBinding.btnAddEdit.setOnClickListener {
            addEditContactDetail(action, position)
        }
    }

    private fun addEditContactDetail(action: String, position: Int) {

        val name = nameInput.text.toString().trim()
        val address = addressInput.text.toString().trim()
        val phone = phoneInput.text.toString().trim()

        if (name.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()) {
            if (action == AppConstants.ADD_ACTION) {
                val contact = ContactDetails(
                    name = name,
                    address = address,
                    phoneNumber = phone
                )
                presenter.addContactList(contact)
            } else {
                val contact = ContactDetails()
                contact.contactId = cId
                contact.name = name
                contact.address = address
                contact.phoneNumber = phone
                presenter.updateContactList(contact, position)
            }
        }
        alertDialog.dismiss()
    }

    //Getting Database reference from AppDatabase.class
    private fun getDBReference() = AppDatabase.getDatabase(applicationContext)
}