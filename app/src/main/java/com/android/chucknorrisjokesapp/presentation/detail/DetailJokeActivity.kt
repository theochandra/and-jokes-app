package com.android.chucknorrisjokesapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivityDetailJokeBinding
import com.android.chucknorrisjokesapp.di.Injector
import com.android.chucknorrisjokesapp.presentation.mapper.map
import com.android.chucknorrisjokesapp.presentation.model.JokeVM
import com.android.domain.Result
import javax.inject.Inject

class DetailJokeActivity : BaseActivity() {

    companion object {
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"
        private const val EXTRA_PARCEL_JOKE = "EXTRA_PARCEL_JOKE"

        @JvmStatic
        fun newIntent(context: Context, category: String): Intent {
            val intent = Intent(context, DetailJokeActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, category)
            return intent
        }

        @JvmStatic
        fun newIntent(context: Context, jokeVM: JokeVM): Intent {
            val intent = Intent(context, DetailJokeActivity::class.java)
            intent.putExtra(EXTRA_PARCEL_JOKE, jokeVM)
            return intent
        }
    }

    private var category: String? = null
    private var jokeVM: JokeVM? = null

    private fun getExtraData() {
        intent.getStringExtra(EXTRA_CATEGORY)?.let {
            category = it
        }

        jokeVM = intent.getParcelableExtra(EXTRA_PARCEL_JOKE)
    }

    @Inject
    lateinit var factory: DetailJokeViewModelFactory

    private lateinit var viewModel: DetailJokeViewModel
    private lateinit var binding: ActivityDetailJokeBinding

//    private val mapper = JokeVmMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_joke)

        (application as Injector).createDetailSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(DetailJokeViewModel::class.java)

        binding.viewModel = viewModel

        getExtraData()
        initJoke()
        getRandomJokeByCategory()
        observeStateData()

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initJoke() {
        binding.joke = jokeVM
    }

    private fun getRandomJokeByCategory() {
        category?.let {
            viewModel.getRandomJokeByCategory(it)
        }
    }

    private fun observeStateData() {
        viewModel.stateData.observe(this, { result ->
            when(result) {
                Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val jokeVM = map(result.data)
                    binding.joke = jokeVM
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Result.Exception -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

}