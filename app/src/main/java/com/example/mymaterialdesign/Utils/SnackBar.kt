package com.example.mymaterialdesign.Utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, duration).show()
}

fun View.showSnackBar(
    text: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String? = null,
    block: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackBar.setAction(actionText) {
            block()
        }
    }
    snackBar.show()
}