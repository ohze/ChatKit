package com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages

import android.view.View

import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.sample.common.data.model.Message

/*
 * Created by troy379 on 05.04.17.
 */
class CustomOutcomingImageMessageViewHolder(itemView: View) : MessageHolders.OutcomingImageMessageViewHolder<Message>(itemView) {

    override fun onBind(data: Message) {
        super.onBind(data)

        time.text = data.status + " " + time.text
    }
}