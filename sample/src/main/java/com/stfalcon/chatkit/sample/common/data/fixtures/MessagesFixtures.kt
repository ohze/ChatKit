package com.stfalcon.chatkit.sample.common.data.fixtures

import com.stfalcon.chatkit.sample.common.data.model.Message
import com.stfalcon.chatkit.sample.common.data.model.User

import java.util.ArrayList
import java.util.Calendar
import java.util.Date

/*
 * Created by troy379 on 12.12.16.
 */
val imageMessage: Message
    get() {
        val message = Message(randomId, user, null)
        message.setImage(Message.Image(randomImage))
        return message
    }

val voiceMessage: Message
    get() {
        val message = Message(randomId, user, null)
        message.voice = Message.Voice("http://example.com", rnd.nextInt(200) + 30)
        return message
    }

val textMessage: Message
    get() = getTextMessage(randomMessage)

fun getTextMessage(text: String) = Message(randomId, user, text)

fun getMessages(startDate: Date?): ArrayList<Message> {
    val messages = ArrayList<Message>()
    for (it in 0..9/*days count*/) {
        val countPerDay = rnd.nextInt(5) + 1

        for (j in 0 until countPerDay) {
            val message: Message = if (it % 2 == 0 && j % 3 == 0) {
                imageMessage
            } else {
                textMessage
            }

            val calendar = Calendar.getInstance()
            if (startDate != null) calendar.time = startDate
            calendar.add(Calendar.DAY_OF_MONTH, -(it * it + 1))

            message.createdAt = calendar.time
            messages.add(message)
        }
    }
    return messages
}

private val user: User
    get() {
        val even = rnd.nextBoolean()
        return User(
                if (even) "0" else "1",
                if (even) names[0] else names[1],
                if (even) avatars[0] else avatars[1],
                true)
    }
