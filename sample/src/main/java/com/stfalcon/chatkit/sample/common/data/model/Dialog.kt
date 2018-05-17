package com.stfalcon.chatkit.sample.common.data.model

import com.stfalcon.chatkit.commons.models.IDialog

/*
 * Created by troy379 on 04.04.17.
 */
class Dialog(override val id: String,
             override val dialogName: String,
             override val dialogPhoto: String,
             override val users: List<User>,
             override var lastMessage: Message,
             override var unreadCount: Int) : IDialog<Message>
