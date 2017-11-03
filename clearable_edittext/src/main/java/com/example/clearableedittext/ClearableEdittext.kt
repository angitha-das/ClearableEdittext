package com.example.clearableedittext

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText


/**
 * Created by angitha on 3/11/17.
 */
class ClearableEdittext:android.support.v7.widget.AppCompatEditText, View.OnTouchListener, View.OnFocusChangeListener,TextWatcherAdapter.TextWatcherListener{
    private var dRight: Drawable? = null
    private var icon: Drawable? = null
    private var clear = false

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        processAttr(context, attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        processAttr(context, attrs)
        init()
    }

    private fun processAttr(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ClearableEdittext, 0, 0)
        clear = ta.getBoolean(R.styleable.ClearableEdittext_clear, false)
        icon = ta.getDrawable(R.styleable.ClearableEdittext_clear_icon)
        ta.recycle()
    }

    private fun init() {
        if (clear) {
            clearFunctionality()
        }
    }

    private fun clearFunctionality() {
        checkCompoundDrawable()
        handleClearButton()
        this.onFocusChangeListener = this
        this.setOnTouchListener(this)
        this.addTextChangedListener(TextWatcherAdapter(this, this))
    }

    private fun checkCompoundDrawable() {
        dRight = if (icon != null) {
            icon //compoundDrawables[2];
        } else {

            ResourcesCompat.getDrawable(resources, R.drawable.ic_clear_black_24dp, null)
        }
        dRight!!.setBounds(0, 0, dRight!!.intrinsicWidth, dRight!!.intrinsicHeight)
    }

    private fun handleClearButton() {
        if (TextUtils.isEmpty(this.text)) {
            this.setCompoundDrawables(this.compoundDrawables[0], this.compoundDrawables[1], null, this.compoundDrawables[3])
        } else {
            this.setCompoundDrawables(this.compoundDrawables[0], this.compoundDrawables[1], dRight, this.compoundDrawables[3])
        }
    }

    private fun handleFocus(hasFocus: Boolean?) {
        if ((!hasFocus!!)) {
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], null, compoundDrawables[3])
        } else {
            if (TextUtils.isEmpty(this.text)) {
                setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], null, compoundDrawables[3])
            } else {
                setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], dRight, compoundDrawables[3])
            }
        }
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        this@ClearableEdittext.handleFocus(hasFocus)
    }


    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val et = this@ClearableEdittext
        if (et.compoundDrawables[2] == null)
            return false

        if (motionEvent.action != MotionEvent.ACTION_UP)
            return false

        if (motionEvent.x > et.width - et.paddingRight - dRight!!.intrinsicWidth) {
            et.text = null
            this@ClearableEdittext.handleClearButton()
        }
        return false
    }

    override fun onTextChanged(view: EditText, text: String) {
        this@ClearableEdittext.handleClearButton()
    }

}