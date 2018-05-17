package com.stfalcon.chatkit.sample.common.data.fixtures

import java.security.SecureRandom
import java.util.UUID

/*
 * Created by Anton Bevza on 1/13/17.
 */
var rnd = SecureRandom()
    private set

val avatars = listOf(
        "http://i.imgur.com/pv1tBmT.png",
        "http://i.imgur.com/R3Jm1CL.png",
        "http://i.imgur.com/ROz4Jgh.png",
        "http://i.imgur.com/Qn9UesZ.png"
)
val groupChatImages = listOf(
        "http://i.imgur.com/hRShCT3.png",
        "http://i.imgur.com/zgTUcL3.png")

val groupChatTitles = listOf(
        "Samuel, Michelle",
        "Jordan, Jordan, Zoe",
        "Julia, Angel, Kyle, Jordan")

val names = listOf(
        "Samuel Reynolds",
        "Kyle Hardman",
        "Zoe Milton",
        "Angel Ogden",
        "Zoe Milton",
        "Angelina Mackenzie",
        "Kyle Oswald",
        "Abigail Stevenson",
        "Julia Goldman",
        "Jordan Gill",
        "Michelle Macey")

val messages = listOf(
        "Hello!",
        "This is my phone number - +1 (234, 567-89-01",
        "Here is my e-mail - myemail@example.com",
        "Hey! Check out this awesome link! www.github.com",
        "Hello! No problem. I can today at 2 pm. And after we can go to the office.",
        "At first, for some time, I was not able to answer him one word",
        "At length one of them called out in a clear, polite, smooth dialect, not unlike in sound to the Italian",
        "By the bye, Bob, said Hopkins",
        "He made his passenger captain of one, with four of the men; and himself, his mate, and five more, went in the other; and they contrived their business very well, for they came up to the ship about midnight.",
        "So saying he unbuckled his baldric with the bugle",
        "Just then her head struck against the roof of the hall: in fact she was now more than nine feet high, and she at once took up the little golden key and hurried off to the garden door.")

val images = listOf(
        "https://habrastorage.org/getpro/habr/post_images/e4b/067/b17/e4b067b17a3e414083f7420351db272b.jpg",
        "http://www.designboom.com/wp-content/uploads/2015/11/stefano-boeri-architetti-vertical-forest-residential-tower-lausanne-switzerland-designboom-01.jpg")

val randomId: String
    get() = UUID.randomUUID().leastSignificantBits.toString()

val randomAvatar: String
    get() = avatars[rnd.nextInt(avatars.size)]

val randomGroupChatImage: String
    get() = groupChatImages[rnd.nextInt(groupChatImages.size)]

val randomGroupChatTitle: String
    get() = groupChatTitles[rnd.nextInt(groupChatTitles.size)]

val randomName: String
    get() = names[rnd.nextInt(names.size)]

val randomMessage: String
    get() = messages[rnd.nextInt(messages.size)]

val randomImage: String
    get() = images[rnd.nextInt(images.size)]

val randomBoolean: Boolean
    get() = rnd.nextBoolean()