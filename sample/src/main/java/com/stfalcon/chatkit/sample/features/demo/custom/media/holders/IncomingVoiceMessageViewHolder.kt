package com.stfalcon.chatkit.sample.features.demo.custom.media.holders

import android.view.View
import android.widget.TextView

import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.model.Message
import com.stfalcon.chatkit.sample.utils.durationString
import com.stfalcon.chatkit.utils.DateFormatter
import com.stfalcon.chatkit.utils.*

/*
 * Created by troy379 on 05.04.17.
 */
class IncomingVoiceMessageViewHolder(itemView: View) : MessageHolders.IncomingTextMessageViewHolder<Message>(itemView) {

    private val tvDuration: TextView = itemView.findViewById(R.id.duration)
    private val tvTime: TextView = itemView.findViewById(R.id.time)

    override fun onBind(data: Message) {
        super.onBind(data)
        tvDuration.text = data.voice!!.duration.durationString()
        tvTime.text = data.createdAt.format(DateFormatter.Template.TIME)
    }
}
