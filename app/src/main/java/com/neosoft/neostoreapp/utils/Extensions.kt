package com.neosoft.neostoreapp.utils

import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

fun EditText.isNull() {
    this.requestFocus()
    this.error = "${this.hint} should not be empty"
}

fun RadioGroup.isNotSelected() {
    Toast.makeText(context, "Select Gender", Toast.LENGTH_SHORT).show()
    this.requestFocus()

}

fun CheckBox.isNotChecked() {
    Toast.makeText(context, "Accept the terms to proceed", Toast.LENGTH_SHORT).show()
    this.error = "Accept the terms to proceed"
}

fun Log.showLog(msg: String) {
    Log.d("Message",msg)
}