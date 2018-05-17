package com.stfalcon.chatkit.sample.features.demo.def

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.common.data.model.Dialog
import com.stfalcon.chatkit.sample.common.data.model.Message
import com.stfalcon.chatkit.sample.features.demo.DemoDialogsActivity

import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_default_dialogs.*

class DefaultDialogsActivity : DemoDialogsActivity() {

    private val dialogs: ArrayList<Dialog>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_dialogs)
        initAdapter()
    }

    override fun onDialogClick(dialog: Dialog) {
        DefaultMessagesActivity.open(this)
    }

    private fun initAdapter() {
        super.dialogsAdapter = DialogsListAdapter(super.imageLoader)
        super.dialogsAdapter.setItems(dialogs)

        super.dialogsAdapter.setOnDialogClickListener(this)
        super.dialogsAdapter.setOnDialogLongClickListener(this)

        dialogsList.setAdapter(super.dialogsAdapter)
    }

    //for example
    private fun onNewMessage(dialogId: String, message: Message) {
        val isUpdated = dialogsAdapter.updateDialogWithMessage(dialogId, message)
        if (!isUpdated) {
            //Dialog with this ID doesn't exist, so you can create new Dialog or update all dialogs list
        }
    }

    //for example
    private fun onNewDialog(dialog: Dialog) {
        dialogsAdapter.addItem(dialog)
    }

    companion object {

        fun open(context: Context) {
            context.startActivity(Intent(context, DefaultDialogsActivity::class.java))
        }
    }
}
