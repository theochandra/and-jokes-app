package com.android.chucknorrisjokesapp.presentation.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivityJokeCategoryBinding
import com.android.chucknorrisjokesapp.presentation.detail.DetailJokeActivity
import com.android.chucknorrisjokesapp.presentation.search.SearchJokeActivity

class JokeCategoryActivity : BaseActivity() {

    companion object {
        private const val EXTRA_CATEGORIES = "EXTRA_CATEGORIES"

        @JvmStatic
        fun newIntent(context: Context, categories: ArrayList<String>): Intent {
            val intent = Intent(context, JokeCategoryActivity::class.java)
            intent.putStringArrayListExtra(EXTRA_CATEGORIES, categories)
            return intent
        }
    }

    private lateinit var binding: ActivityJokeCategoryBinding

    private lateinit var categories: ArrayList<String>

    private fun getExtraData() {
        categories = intent.getStringArrayListExtra(EXTRA_CATEGORIES)
        categoryAdapter.setCategoryList(categories)
    }

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_joke_category)

        initRecyclerView()
        getExtraData()
        searchClicked()
    }

    private fun initRecyclerView() {
        categoryAdapter = CategoryAdapter {
            category: String -> categoryItemClicked(category)
        }
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(this@JokeCategoryActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

    private fun categoryItemClicked(category: String) {
        val intent = DetailJokeActivity.newIntent(this, category)
        startActivity(intent)
    }

    private fun searchClicked() {
        binding.etQuery.setOnClickListener {
            val intent = Intent(this, SearchJokeActivity::class.java)
            startActivity(intent)
        }
    }

}