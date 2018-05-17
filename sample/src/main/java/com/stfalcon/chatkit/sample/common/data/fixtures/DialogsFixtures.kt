package com.stfalcon.chatkit.sample.common.data.fixtures

import com.stfalcon.chatkit.sample.common.data.model.Dialog
import com.stfalcon.chatkit.sample.common.data.model.Message
import com.stfalcon.chatkit.sample.common.data.model.User

import java.util.Calendar
import java.util.Date

/*
 * Created by Anton Bevza on 07.09.16.
 */
val dialogs: List<Dialog>
    get() = (0..19).map {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -(it * it))
        calendar.add(Calendar.MINUTE, -(it * it))

       getDialog(it, calendar.time)
    }

private fun getDialog(i: Int, lastMessageCreatedAt: Date): Dialog {
    val users = users
    return Dialog(
            randomId,
            if (users.size > 1) groupChatTitles[users.size - 2] else users[0].name,
            if (users.size > 1) groupChatImages[users.size - 2] else randomAvatar,
            users,
            getMessage(lastMessageCreatedAt),
            if (i < 3) 3 - i else 0)
}

private val users: List<User>
    get() = (0 .. rnd.nextInt(4)).map { user }

private val user: User
    get() = User(randomId, randomName, randomAvatar, randomBoolean)

private fun getMessage(date: Date): Message = Message(randomId, user, randomMessage, date)
