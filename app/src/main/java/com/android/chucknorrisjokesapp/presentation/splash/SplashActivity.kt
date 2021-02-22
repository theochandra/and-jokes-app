package com.android.chucknorrisjokesapp.presentation.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.R
import com.android.chucknorrisjokesapp.base.BaseActivity
import com.android.chucknorrisjokesapp.databinding.ActivitySplashBinding
import com.android.chucknorrisjokesapp.di.Injector
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

    }
}