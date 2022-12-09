package com.mukesh.apicallingsample.presentation.login.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mukesh.apicallingsample.data.repository.AppRepository

class LoginViewModelFactory (private val app: Application, private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(app,repository) as T
    }
}