package com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.dialogs

import android.view.View

import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.model.Dialog

/*
 * Created by Anton Bevza on 1/18/17.
 */
class CustomDialogViewHolder(itemView: View) : DialogsListAdapter.DialogViewHolder<Dialog>(itemView) {
    private val onlineIndicator: View = itemView.findViewById(R.id.onlineIndicator)

    override fun onBind(data: Dialog) {
        super.onBind(data)

        if (data.users.size > 1) {
            onlineIndicator.visibility = View.GONE
        } else {
            val isOnline = data.users[0].isOnline
            onlineIndicator.visibility = View.VISIBLE
            if (isOnline) {
                onlineIndicator.setBackgroundResource(R.drawable.shape_bubble_online)
            } else {
                onlineIndicator.setBackgroundResource(R.drawable.shape_bubble_offline)
            }
        }
    }
}
