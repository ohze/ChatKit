package com.stfalcon.chatkit.sample.common.data.fixtures;

import com.stfalcon.chatkit.sample.common.data.model.Message;
import com.stfalcon.chatkit.sample.common.data.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by troy379 on 12.12.16.
 */
public final class MessagesFixtures {
    private MessagesFixtures() {
        throw new AssertionError();
    }

    public static Message getImageMessage() {
        Message message = new Message(FixturesDataKt.getRandomId(), getUser(), null);
        message.setImage(new Message.Image(FixturesDataKt.getRandomImage()));
        return message;
    }

    public static Message getVoiceMessage() {
        Message message = new Message(FixturesDataKt.getRandomId(), getUser(), null);
        message.setVoice(new Message.Voice("http://example.com", FixturesDataKt.getRnd().nextInt(200) + 30));
        return message;
    }

    public static Message getTextMessage() {
        return getTextMessage(FixturesDataKt.getRandomMessage());
    }

    public static Message getTextMessage(String text) {
        return new Message(FixturesDataKt.getRandomId(), getUser(), text);
    }

    public static ArrayList<Message> getMessages(Date startDate) {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10/*days count*/; i++) {
            int countPerDay = FixturesDataKt.getRnd().nextInt(5) + 1;

            for (int j = 0; j < countPerDay; j++) {
                Message message;
                if (i % 2 == 0 && j % 3 == 0) {
                    message = getImageMessage();
                } else {
                    message = getTextMessage();
                }

                Calendar calendar = Calendar.getInstance();
                if (startDate != null) calendar.setTime(startDate);
                calendar.add(Calendar.DAY_OF_MONTH, -(i * i + 1));

                message.setCreatedAt(calendar.getTime());
                messages.add(message);
            }
        }
        return messages;
    }

    private static User getUser() {
        boolean even = FixturesDataKt.getRnd().nextBoolean();
        return new User(
                even ? "0" : "1",
                even ? FixturesDataKt.getNames().get(0) : FixturesDataKt.getNames().get(1),
                even ? FixturesDataKt.getAvatars().get(0) : FixturesDataKt.getAvatars().get(1),
                true);
    }
}
