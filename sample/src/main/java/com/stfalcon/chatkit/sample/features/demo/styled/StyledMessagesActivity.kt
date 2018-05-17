package com.stfalcon.chatkit.sample.features.demo.styled

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.fixtures.*
import com.stfalcon.chatkit.sample.features.demo.DemoMessagesActivity
import com.stfalcon.chatkit.utils.DateFormatter
import com.stfalcon.chatkit.utils.*

import java.util.Date
import kotlinx.android.synthetic.main.activity_styled_messages.*

class StyledMessagesActivity : DemoMessagesActivity(), MessageInput.InputListener, MessageInput.AttachmentsListener, DateFormatter.Formatter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_styled_messages)

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

    override fun format(date: Date): String {
        return if (DateFormatter.isToday(date)) {
            getString(R.string.date_header_today)
        } else if (DateFormatter.isYesterday(date)) {
            getString(R.string.date_header_yesterday)
        } else {
            date.format(DateFormatter.Template.STRING_DAY_MONTH_YEAR)
        }
    }

    private fun initAdapter() {
        super.messagesAdapter = MessagesListAdapter(super.senderId, super.imageLoader)
        super.messagesAdapter.enableSelectionMode(this)
        super.messagesAdapter.setLoadMoreListener(this)
        super.messagesAdapter.setDateHeadersFormatter(this)
        messagesList.setAdapter(super.messagesAdapter)
    }

    companion object {

        fun open(context: Context) {
            context.startActivity(Intent(context, StyledMessagesActivity::class.java))
        }
    }
}
