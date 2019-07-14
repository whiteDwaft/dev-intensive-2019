package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.R.attr.bottom
import android.graphics.Rect


fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (currentFocus == null) {
        imm.hideSoftInputFromWindow(View(this).windowToken, 0)
    }
    else
    {
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
fun Activity.isKeyboardOpen():Boolean{
    val r = Rect()
    val rootview = this.window.decorView // this = activity
    rootview.getWindowVisibleDisplayFrame(r)
    val keyboardHeight = rootview.height - r.bottom
    return keyboardHeight > 0
}
fun Activity.isKeyboardClosed():Boolean{
    val r = Rect()
    val rootview = this.window.decorView // this = activity
    rootview.getWindowVisibleDisplayFrame(r)
    val keyboardHeight = rootview.height - r.bottom
    return keyboardHeight == 0
}