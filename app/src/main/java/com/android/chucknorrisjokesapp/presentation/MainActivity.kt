package com.android.chucknorrisjokesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}