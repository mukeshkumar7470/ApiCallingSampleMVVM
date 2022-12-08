package com.mukesh.apicallingsample.presentation.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.databinding.ActivityMainBinding
import com.mukesh.apicallingsample.presentation.login.viewModels.LoginViewModel
import com.mukesh.apicallingsample.presentation.login.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appRepository = AppRepository(applicationContext)
        viewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(application, appRepository)
        )[LoginViewModel::class.java]

        binding.cvLogin.setOnClickListener {
            val email = binding.etMobileNumber.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
            /*when {
                email.isEmpty() -> {
                    binding.etMobileNumber.error = "Empty Email"
                }
                password.isEmpty() -> {
                    binding.etPassword.error = "Empty Password"
                }
                else -> {
                    viewModel.login(email, password)
                }
            }*/


        }
    }
}