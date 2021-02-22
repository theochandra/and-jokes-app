package com.android.chucknorrisjokesapp.presentation.category

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.chucknorrisjokesapp.R

class JokeCategoryActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CATEGORIES = "EXTRA_CATEGORIES"

        @JvmStatic
        fun newIntent(context: Context, categories: ArrayList<String>): Intent {
            val intent = Intent(context, JokeCategoryActivity::class.java)
            intent.putStringArrayListExtra(EXTRA_CATEGORIES, categories)
            return intent
        }
    }

    private lateinit var categories: ArrayList<String>

    private fun getExtraData() {
        categories = intent.getStringArrayListExtra(EXTRA_CATEGORIES)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_category)

        getExtraData()

        for (category in categories) {
            Log.i("JokeCategoryActivity", "Category ::: $category")
        }
    }
}