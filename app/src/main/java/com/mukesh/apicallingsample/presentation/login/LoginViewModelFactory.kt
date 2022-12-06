package com.mukesh.apicallingsample.presentation.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.presentation.login.viewModels.LoginViewModel

class LoginViewModelFactory (private val app: Application, private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(app,repository) as T
    }
}