package com.android.chucknorrisjokesapp.presentation.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivitySplashBinding
import com.android.chucknorrisjokesapp.di.Injector
import com.android.chucknorrisjokesapp.presentation.category.JokeCategoryActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var factory: SplashViewModelFactory

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        (application as Injector).createSplashSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(SplashViewModel::class.java)

        binding.viewModel = viewModel

        observeCategories()
        observeError()
        observeException()
    }

    private fun observeCategories() {
        viewModel.categories.observe(this, { result ->
            val categories: ArrayList<String> = result as ArrayList<String>
            val intent = JokeCategoryActivity.newIntent(this, categories)
            startActivity(intent)
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