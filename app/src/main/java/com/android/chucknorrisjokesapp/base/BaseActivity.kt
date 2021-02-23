package com.android.chucknorrisjokesapp.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun showAlertMessage(title: String, message: String, btnLabel: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(btnLabel) { dialog, _ -> dialog.dismiss() }
            .show()
    }

}