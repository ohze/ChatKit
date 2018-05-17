package com.stfalcon.chatkit.sample.features.demo.custom.holder

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
import com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages.CustomIncomingImageMessageViewHolder
import com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages.CustomIncomingTextMessageViewHolder
import com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages.CustomOutcomingImageMessageViewHolder
import com.stfalcon.chatkit.sample.features.demo.custom.holder.holders.messages.CustomOutcomingTextMessageViewHolder
import com.stfalcon.chatkit.sample.utils.AppUtils
import kotlinx.android.synthetic.main.activity_custom_holder_messages.*

class CustomHolderMessagesActivity : DemoMessagesActivity(),
        MessagesListAdapter.OnMessageLongClickListener<Message>,
        MessageInput.InputListener, MessageInput.AttachmentsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_holder_messages)

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
        AppUtils.showToast(this, R.string.on_log_click_message, false)
    }

    private fun initAdapter() {
        val holdersConfig = MessageHolders()
                .setIncomingTextConfig(
                        CustomIncomingTextMessageViewHolder::class.java,
                        R.layout.item_custom_incoming_text_message)
                .setOutcomingTextConfig(
                        CustomOutcomingTextMessageViewHolder::class.java,
                        R.layout.item_custom_outcoming_text_message)
                .setIncomingImageConfig(
                        CustomIncomingImageMessageViewHolder::class.java,
                        R.layout.item_custom_incoming_image_message)
                .setOutcomingImageConfig(
                        CustomOutcomingImageMessageViewHolder::class.java,
                        R.layout.item_custom_outcoming_image_message)

        super.messagesAdapter = MessagesListAdapter(super.senderId, holdersConfig, super.imageLoader)
        super.messagesAdapter.setOnMessageLongClickListener(this)
        super.messagesAdapter.setLoadMoreListener(this)
        messagesList.setAdapter(super.messagesAdapter)
    }

    companion object {

        fun open(context: Context) {
            context.startActivity(Intent(context, CustomHolderMessagesActivity::class.java))
        }
    }
}
