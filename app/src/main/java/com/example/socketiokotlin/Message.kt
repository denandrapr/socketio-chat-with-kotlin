package com.example.socketiokotlin

data class Message(
    val mType: Int,
    val mMessage: String?,
    val mUsername: String?
)