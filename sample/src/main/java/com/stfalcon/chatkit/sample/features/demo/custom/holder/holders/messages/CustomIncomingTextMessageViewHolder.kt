package com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages

import android.view.View

import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.model.Message

class CustomIncomingTextMessageViewHolder(itemView: View) : MessageHolders.IncomingTextMessageViewHolder<Message>(itemView) {

    private val onlineIndicator: View = itemView.findViewById(R.id.onlineIndicator)

    override fun onBind(data: Message) {
        super.onBind(data)

        val isOnline = data.user.isOnline
        if (isOnline) {
            onlineIndicator.setBackgroundResource(R.drawable.shape_bubble_online)
        } else {
            onlineIndicator.setBackgroundResource(R.drawable.shape_bubble_offline)
        }
    }
}
