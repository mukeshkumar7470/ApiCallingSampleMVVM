package com.mukesh.apicallingsample.presentation.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mukesh.apicallingsample.data.Response
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.databinding.ActivityMainBinding
import com.mukesh.apicallingsample.observe
import com.mukesh.apicallingsample.presentation.login.LoginViewModelFactory
import com.mukesh.apicallingsample.presentation.login.model.LoginResponse
import com.mukesh.apicallingsample.presentation.login.model.TokenResponse
import com.mukesh.apicallingsample.presentation.login.viewModels.LoginViewModel

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

        observe(viewModel.loginData, ::handleResultToken)

        binding.cvLogin.setOnClickListener {
            val email = binding.etMobileNumber.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                email.isEmpty() -> {
                    binding.etMobileNumber.error = "Empty Email"
                }
                password.isEmpty() -> {
                    binding.etPassword.error = "Empty Password"
                }
                else -> {
                    viewModel.callToken(
                        email, password
                    )
                }
            }
        }
    }

    private fun handleResultToken(it: Response<LoginResponse>) {
        when (it) {
            is Response.Loading -> {
                binding.liLoginUi.visibility =  View.GONE
                binding.progressCircular.visibility =  View.VISIBLE
            }
            is Response.Success -> {
                it.data?.let {
                    Log.e("TAG", "handleResultToken: $it")
                    Toast.makeText(this@LoginActivity, "Successfully  Logged in...", Toast.LENGTH_SHORT).show()
                  //  binding.liLoginUi.visibility =  View.GONE
                 //   binding.progressCircular.visibility =  View.VISIBLE
                    binding.liLoginUi.visibility =  View.GONE
                    binding.progressCircular.visibility =  View.GONE
                    binding.tvUserName.visibility =  View.VISIBLE

                    binding.tvUserName.setText(it.token)

                }
            }
            is Response.Error -> {
                binding.liLoginUi.visibility =  View.VISIBLE
                binding.progressCircular.visibility =  View.GONE
            }
        }
    }


}