package com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages

import android.view.View

import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.sample.common.data.model.Message

class CustomOutcomingTextMessageViewHolder(itemView: View) : MessageHolders.OutcomingTextMessageViewHolder<Message>(itemView) {

    override fun onBind(data: Message) {
        super.onBind(data)

        time.text = data.status + " " + time.text
    }
}
