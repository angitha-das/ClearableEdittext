package com.example.angitha.clearableedittext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.example.clearableedittext.ClearableEdittext
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edittext = ClearableEdittext(this)
        edittext.enableClearable(true)
        edittext.setClearDrawable(ContextCompat.getDrawable(this,R.drawable.ic_delete_black_24dp))
        linearlayout.addView(edittext)
    }
}
