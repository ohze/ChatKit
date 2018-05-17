package com.stfalcon.chatkit.sample.features.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.stfalcon.chatkit.sample.common.data.model.Dialog
import com.stfalcon.chatkit.sample.utils.AppUtils

/*
 * Created by troy379 on 05.04.17.
 */
abstract class DemoDialogsActivity : AppCompatActivity(),
        DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog> {

    protected lateinit var imageLoader: ImageLoader
    protected lateinit var dialogsAdapter: DialogsListAdapter<Dialog>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageLoader = PicasoImageLoader()
    }

    override fun onDialogLongClick(dialog: Dialog) {
        AppUtils.showToast(
                this,
                dialog.dialogName,
                false)
    }
}
