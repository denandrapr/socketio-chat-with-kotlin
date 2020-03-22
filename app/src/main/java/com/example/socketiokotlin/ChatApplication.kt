package com.example.socketiokotlin

import android.app.Application
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException


class ChatApplication : Application() {

    private var mSocket: Socket? = null
    val socket: Socket?
        get() = mSocket

    init {
        mSocket = try {
            IO.socket(Constants.CHAT_SERVER_URL)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }
}