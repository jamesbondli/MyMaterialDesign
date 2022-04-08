package com.example.mymaterialdesign.Utils

import android.view.View
import com.example.mymaterialdesign.showToast
import com.google.android.material.snackbar.Snackbar

fun String.showSnackBar(view: View, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, this, duration).show()
}

fun String.showSnackBar(
    view: View,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String = "Undo",
    tips: String
) {
    Snackbar.make(view, this, duration)
        .setAction(actionText) {
            tips.showToast()
        }.show()
}