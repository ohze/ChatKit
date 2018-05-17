package com.stfalcon.chatkit.sample.features.demo.custom.media

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle

import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.fixtures.*
import com.stfalcon.chatkit.sample.common.data.model.Message
import com.stfalcon.chatkit.sample.features.demo.DemoMessagesActivity
import com.stfalcon.chatkit.sample.features.demo.custom.media.holders.IncomingVoiceMessageViewHolder
import com.stfalcon.chatkit.sample.features.demo.custom.media.holders.OutcomingVoiceMessageViewHolder
import kotlinx.android.synthetic.main.activity_custom_media_messages.*

class CustomMediaMessagesActivity : DemoMessagesActivity(),
        MessageInput.InputListener,
        MessageInput.AttachmentsListener,
        MessageHolders.ContentChecker<Message>,
        DialogInterface.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_media_messages)

        initAdapter()

        input.setInputListener(this)
        input.setAttachmentsListener(this)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        super.messagesAdapter.addToStart(
                getTextMessage(input!!.toString()), true)
        return true
    }

    override fun onAddAttachments() {
        AlertDialog.Builder(this)
                .setItems(R.array.view_types_dialog, this)
                .show()
    }

    override fun hasContentFor(message: Message, type: Byte): Boolean {
        when (type) {
            CONTENT_TYPE_VOICE -> return (message.voice != null
                    && message.voice!!.url != null
                    && !message.voice!!.url.isEmpty())
        }
        return false
    }

    override fun onClick(dialogInterface: DialogInterface, i: Int) {
        when (i) {
            0 -> messagesAdapter.addToStart(imageMessage, true)
            1 -> messagesAdapter.addToStart(voiceMessage, true)
        }
    }

    private fun initAdapter() {
        val holders = MessageHolders()
                .registerContentType(
                        CONTENT_TYPE_VOICE,
                        IncomingVoiceMessageViewHolder::class.java,
                        R.layout.item_custom_incoming_voice_message,
                        OutcomingVoiceMessageViewHolder::class.java,
                        R.layout.item_custom_outcoming_voice_message,
                        this)


        super.messagesAdapter = MessagesListAdapter(super.senderId, holders, super.imageLoader)
        super.messagesAdapter.enableSelectionMode(this)
        super.messagesAdapter.setLoadMoreListener(this)
        this.messagesList.setAdapter(super.messagesAdapter)
    }

    companion object {

        private const val CONTENT_TYPE_VOICE: Byte = 1

        fun open(context: Context) {
            context.startActivity(Intent(context, CustomMediaMessagesActivity::class.java))
        }
    }
}
