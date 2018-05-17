package com.stfalcon.chatkit.sample.common.data.model

import com.stfalcon.chatkit.commons.models.IUser

/*
 * Created by troy379 on 04.04.17.
 */
class User(
        override val id: String,
        override val name: String,
        override val avatar: String,
        val isOnline: Boolean) : IUser
