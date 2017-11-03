package com.example.clearableedittext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by angitha on 3/11/17.
 */
internal class TextWatcherAdapter(private val view: EditText, private val listener: TextWatcherListener) : TextWatcher {

    internal interface TextWatcherListener {

        fun onTextChanged(view: EditText, text: String)

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        listener.onTextChanged(view, s.toString())
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // pass
    }

    override fun afterTextChanged(s: Editable) {
        // pass
    }

}