package com.example.socketiokotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class MessageAdapter constructor(context: Context,
                                 messages: List<Message>?)
    : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var mMessages: List<Message>? = null

    companion object {
        lateinit var mUsernameColors: IntArray
    }

    init {
        mMessages = messages
        mUsernameColors = context.resources.getIntArray(R.array.username_colors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layout = -1
        when (viewType) {
            Constants.TYPE_MESSAGE -> layout = R.layout.item_message
            Constants.TYPE_LOG -> layout = R.layout.item_log
            Constants.TYPE_ACTION -> layout = R.layout.item_action
        }
        val v = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val message: Message = mMessages!![position]
        viewHolder.setMessage(message.mMessage)
        viewHolder.setUsername(message.mUsername.toString())
    }

    override fun getItemCount(): Int {
        return mMessages!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return mMessages!![position].mType
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mUsernameView: TextView?
        private val mMessageView: TextView?

        fun setUsername(username: String) {
            if (null == mUsernameView) return
            mUsernameView.text = username
            mUsernameView.setTextColor(getUsernameColor(username))
        }

        fun setMessage(message: String?) {
            if (null == mMessageView) return
            mMessageView.text = message
        }

        private fun getUsernameColor(username: String): Int {
            var hash = 7
            var i = 0
            val len = username.length
            while (i < len) {
                hash = username.codePointAt(i) + (hash shl 5) - hash
                i++
            }
            val index: Int = abs(hash % mUsernameColors.size)
            return mUsernameColors[index]
        }

        init {
            mUsernameView = itemView.findViewById<View>(R.id.username) as TextView
            mMessageView = itemView.findViewById<View>(R.id.message) as TextView
        }
    }
}