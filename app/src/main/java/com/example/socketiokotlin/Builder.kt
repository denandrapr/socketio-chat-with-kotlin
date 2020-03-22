package com.example.socketiokotlin

class Builder constructor(type: Int) {
    private var mType = 0
    private var mUsername: String? = null
    private var mMessage: String? = null

    fun username(username: String): Builder {
        mUsername = username
        return this
    }

    fun message(message: String): Builder {
        mMessage = message
        return this
    }

    fun build(): Message {
        return Message(
            mType,
            mUsername,
            mMessage
        )
    }
}