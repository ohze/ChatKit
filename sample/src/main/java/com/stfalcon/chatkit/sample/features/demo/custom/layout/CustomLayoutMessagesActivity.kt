package com.stfalcon.chatkit.sample.features.demo.custom.layout

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.fixtures.*
import com.stfalcon.chatkit.sample.common.data.model.Message
import com.stfalcon.chatkit.sample.features.demo.DemoMessagesActivity
import com.stfalcon.chatkit.sample.utils.showToast
import kotlinx.android.synthetic.main.activity_custom_layout_messages.*

class CustomLayoutMessagesActivity : DemoMessagesActivity(),
        MessagesListAdapter.OnMessageLongClickListener<Message>,
        MessageInput.InputListener, MessageInput.AttachmentsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_layout_messages)
        initAdapter()

        input.setInputListener(this)
        input.setAttachmentsListener(this)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        messagesAdapter.addToStart(
                getTextMessage(input!!.toString()), true)
        return true
    }

    override fun onAddAttachments() {
        messagesAdapter.addToStart(imageMessage, true)
    }

    override fun onMessageLongClick(message: Message) {
        this.showToast(R.string.on_log_click_message, false)
    }

    private fun initAdapter() {
        val holdersConfig = MessageHolders()
                .setIncomingTextLayout(R.layout.item_custom_incoming_text_message)
                .setOutcomingTextLayout(R.layout.item_custom_outcoming_text_message)
                .setIncomingImageLayout(R.layout.item_custom_incoming_image_message)
                .setOutcomingImageLayout(R.layout.item_custom_outcoming_image_message)

        super.messagesAdapter = MessagesListAdapter(super.senderId, holdersConfig, super.imageLoader)
        super.messagesAdapter.setOnMessageLongClickListener(this)
        super.messagesAdapter.setLoadMoreListener(this)
        messagesList.setAdapter(super.messagesAdapter)
    }

    companion object {

        fun open(context: Context) {
            context.startActivity(Intent(context, CustomLayoutMessagesActivity::class.java))
        }
    }
}
