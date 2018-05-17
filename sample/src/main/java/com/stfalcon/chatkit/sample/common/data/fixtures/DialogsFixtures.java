package com.stfalcon.chatkit.sample.common.data.fixtures;

import com.stfalcon.chatkit.sample.common.data.model.Dialog;
import com.stfalcon.chatkit.sample.common.data.model.Message;
import com.stfalcon.chatkit.sample.common.data.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by Anton Bevza on 07.09.16.
 */
public final class DialogsFixtures {
    private DialogsFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<Dialog> getDialogs() {
        ArrayList<Dialog> chats = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -(i * i));
            calendar.add(Calendar.MINUTE, -(i * i));

            chats.add(getDialog(i, calendar.getTime()));
        }

        return chats;
    }

    private static Dialog getDialog(int i, Date lastMessageCreatedAt) {
        ArrayList<User> users = getUsers();
        return new Dialog(
                FixturesDataKt.getRandomId(),
                users.size() > 1 ? FixturesDataKt.getGroupChatTitles().get(users.size() - 2) : users.get(0).getName(),
                users.size() > 1 ? FixturesDataKt.getGroupChatImages().get(users.size() - 2) : FixturesDataKt.getRandomAvatar(),
                users,
                getMessage(lastMessageCreatedAt),
                i < 3 ? 3 - i : 0);
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        int usersCount = 1 + FixturesDataKt.getRnd().nextInt(4);

        for (int i = 0; i < usersCount; i++) {
            users.add(getUser());
        }

        return users;
    }

    private static User getUser() {
        return new User(
                FixturesDataKt.getRandomId(),
                FixturesDataKt.getRandomName(),
                FixturesDataKt.getRandomAvatar(),
                FixturesDataKt.getRandomBoolean());
    }

    private static Message getMessage(final Date date) {
        return new Message(
                FixturesDataKt.getRandomId(),
                getUser(),
                FixturesDataKt.getRandomMessage(),
                date);
    }
}
