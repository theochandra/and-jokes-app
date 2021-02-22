package com.android.chucknorrisjokesapp.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivitySplashBinding
import com.android.chucknorrisjokesapp.di.Injector
import com.android.domain.Result
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

        observeStateData()
    }

    private fun observeStateData() {
        viewModel.stateData.observe(this, { result ->
            when(result) {
                Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val categories = result.data
                    for (category in categories) {
                        Log.i("SplashActivity", "Category ::: $category")
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showAlertMessage(
                        getString(R.string.label_dialog_title_alert),
                        result.errorMessage,
                        getString(R.string.label_button_oke)
                    )
                }
                is Result.Exception -> {
                    binding.progressBar.visibility = View.GONE
                    result.exception.message?.let { errorMessage ->
                        showAlertMessage(
                            getString(R.string.label_dialog_title_alert),
                            errorMessage,
                            getString(R.string.label_button_oke)
                        )
                    }
                }
            }
        })
    }

}