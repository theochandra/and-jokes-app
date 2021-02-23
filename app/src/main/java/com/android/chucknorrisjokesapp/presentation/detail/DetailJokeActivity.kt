package com.android.chucknorrisjokesapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivityDetailJokeBinding
import com.android.chucknorrisjokesapp.di.Injector
import com.android.chucknorrisjokesapp.presentation.model.JokeVM
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
        fun newIntent(context: Context, category: String, jokeVM: JokeVM): Intent {
            val intent = Intent(context, DetailJokeActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, category)
            intent.putExtra(EXTRA_PARCEL_JOKE, jokeVM)
            return intent
        }
    }

    private lateinit var category: String
    private var jokeVM: JokeVM? = null

    private fun getExtraData() {
        intent.getStringExtra(EXTRA_CATEGORY)?.let {
            category = it
        }
        intent.getParcelableExtra<JokeVM>(EXTRA_PARCEL_JOKE)?.let {
            jokeVM = it
        }
//        jokeVM = intent.getParcelableExtra(EXTRA_PARCEL_JOKE)
    }

    @Inject
    lateinit var factory: DetailJokeViewModelFactory

    private lateinit var viewModel: DetailJokeViewModel
    private lateinit var binding: ActivityDetailJokeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_joke)

        (application as Injector).createDetailSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(DetailJokeViewModel::class.java)

        binding.viewModel = viewModel

        getExtraData()
        initJokeFromExtraData()
        getRandomJokeByCategory()
        observeJoke()
        observeError()
        observeException()

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = category
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initJokeFromExtraData() {
        jokeVM?.let { joke ->
            binding.joke = joke
        }
    }

    private fun getRandomJokeByCategory() {
        if (jokeVM != null) return
        viewModel.getRandomJokeByCategory(category)
    }

    private fun observeJoke() {
        viewModel.joke.observe(this, { jokeVM ->
            binding.joke = jokeVM
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

}