package com.mukesh.apicallingsample.presentation.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.mukesh.apicallingsample.data.Response
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.databinding.ActivityMainBinding
import com.mukesh.apicallingsample.observe
import com.mukesh.apicallingsample.presentation.login.viewModels.LoginViewModel
import com.mukesh.apicallingsample.presentation.login.LoginViewModelFactory
import com.mukesh.apicallingsample.presentation.login.model.LoginResponse

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

        observe(viewModel.loginResponseData, ::handleResult)

        binding.cvLogin.setOnClickListener {
            val email = binding.etMobileNumber.text.toString()
            val password = binding.etPassword.text.toString()
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

            viewModel.login(email, password)


        }
    }
    private fun handleResult(it: Response<LoginResponse>) {
        when (it) {
            is Response.Loading -> {

            }
            is Response.Success -> {
                it.data?.let {
                    // hideProgressDialog()
                    println("LoginResponse::" + Gson().toJson(it))

                }
            }
            is Response.Error -> {
            }
        }
    }

}