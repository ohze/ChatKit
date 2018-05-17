package com.stfalcon.chatkit.sample.features.demo.def

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.fixtures.*
import com.stfalcon.chatkit.sample.features.demo.DemoMessagesActivity
import com.stfalcon.chatkit.sample.utils.AppUtils
import kotlinx.android.synthetic.main.activity_default_messages.*

class DefaultMessagesActivity : DemoMessagesActivity(), MessageInput.InputListener, MessageInput.AttachmentsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_messages)

        initAdapter()

        val input = findViewById<View>(R.id.input) as MessageInput
        input.setInputListener(this)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        super.messagesAdapter.addToStart(
                getTextMessage(input!!.toString()), true)
        return true
    }

    override fun onAddAttachments() {
        super.messagesAdapter.addToStart(
                imageMessage, true)
    }

    private fun initAdapter() {
        super.messagesAdapter = MessagesListAdapter(super.senderId, super.imageLoader)
        super.messagesAdapter.enableSelectionMode(this)
        super.messagesAdapter.setLoadMoreListener(this)
        super.messagesAdapter.registerViewClickListener(R.id.messageUserAvatar
        ) { view, message ->
            AppUtils.showToast(this@DefaultMessagesActivity,
                    message.user.name + " avatar click",
                    false)
        }
        this.messagesList.setAdapter(super.messagesAdapter)
    }

    companion object {

        fun open(context: Context) {
            context.startActivity(Intent(context, DefaultMessagesActivity::class.java))
        }
    }
}
