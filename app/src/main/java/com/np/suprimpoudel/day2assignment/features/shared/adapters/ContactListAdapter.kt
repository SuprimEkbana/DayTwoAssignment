package com.np.suprimpoudel.day2assignment.features.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.suprimpoudel.day2assignment.databinding.ItemContactTileBinding
import com.np.suprimpoudel.day2assignment.features.interfaces.ContactListTileOnClick
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails
import com.np.suprimpoudel.day2assignment.features.shared.view_holders.ContactListViewHolder

class ContactListAdapter(
    private val contactList: ArrayList<ContactDetails>,
    private val listener: ContactListTileOnClick
) : RecyclerView.Adapter<ContactListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        val binding = ItemContactTileBinding.inflate(inflater,parent,false)
        return ContactListViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size
}
