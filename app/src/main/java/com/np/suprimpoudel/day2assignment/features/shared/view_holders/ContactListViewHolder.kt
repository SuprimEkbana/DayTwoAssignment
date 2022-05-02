package com.np.suprimpoudel.day2assignment.features.shared.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.np.suprimpoudel.day2assignment.R
import com.np.suprimpoudel.day2assignment.databinding.ItemContactTileBinding
import com.np.suprimpoudel.day2assignment.features.interfaces.ContactListTileOnClick
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails

class ContactListViewHolder(listener: ContactListTileOnClick, private val binding: ItemContactTileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(contactDetails: ContactDetails) {
        binding.contactDetail = contactDetails
        binding.executePendingBindings()
    }

    init {
        binding.imgDeleteIcon.setOnClickListener {
            listener.onDeleteIconClicked(position = adapterPosition)
        }
        itemView.setOnClickListener {
            listener.onContactListTileClicked(position = adapterPosition)
        }
    }
}