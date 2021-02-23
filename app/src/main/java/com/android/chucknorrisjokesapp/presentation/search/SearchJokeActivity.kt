package com.android.chucknorrisjokesapp.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivitySearchJokeBinding
import com.android.chucknorrisjokesapp.di.Injector
import com.android.chucknorrisjokesapp.presentation.detail.DetailJokeActivity
import com.android.chucknorrisjokesapp.presentation.model.JokeVM
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchJokeActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchJokeActivity::class.java)
        }
    }

    @Inject
    lateinit var factory: SearchJokeViewModelFactory

    private lateinit var viewModel: SearchJokeViewModel
    private lateinit var binding: ActivitySearchJokeBinding
    private lateinit var jokeAdapter: JokeAdapter

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var searchJob: Job? = null

    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_joke)

        (application as Injector).createSearchSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(SearchJokeViewModel::class.java)

        binding.viewModel = viewModel

        initRecyclerView()
        observeQuery()
        observeJokeList()
        observeError()
        observeException()
        onArrowBackPressed()
    }

    private fun initRecyclerView() {
        jokeAdapter = JokeAdapter { 
            jokeVM -> jokeItemClicked(jokeVM)
        }
        binding.rvJoke.apply {
            layoutManager = LinearLayoutManager(this@SearchJokeActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = jokeAdapter
        }
    }

    private fun jokeItemClicked(jokeVM: JokeVM) {
        val intent = DetailJokeActivity.newIntent(this, query, jokeVM)
        startActivity(intent)
    }

    private fun observeQuery() {
        viewModel.getQuery().observe(this, { query ->
            searchJob?.cancel()
            jokeAdapter.clearList()
            viewModel.changeLoadingState(false)

            if (query.isNullOrEmpty()) return@observe
            if (query.length < 3) return@observe
            searchJob = coroutineScope.launch {
                delay(400)
                this@SearchJokeActivity.query = query
                viewModel.getJokesByQuery(query)
            }
        })
    }

    private fun observeJokeList() {
        viewModel.jokeList.observe(this, { jokeList ->
            if (jokeList.isEmpty())
                binding.layoutNotFound.visibility = View.VISIBLE
            else binding.layoutNotFound.visibility = View.GONE
            jokeAdapter.setJokeList(jokeList)
        })
    }

    private fun observeError() {
        viewModel.error.observe(this, { errorMessage ->
            showAlertMessage(
                getString(R.string.label_dialog_title_alert),
                errorMessage,
                getString(R.string.label_button_oke)
            )
        })
    }

    private fun observeException() {
        viewModel.exception.observe(this, { exception ->
            exception.message?.let { errorMessage ->
                showAlertMessage(
                    getString(R.string.label_dialog_title_alert),
                    errorMessage,
                    getString(R.string.label_button_oke)
                )
            }
        })
    }

    private fun onArrowBackPressed() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

}