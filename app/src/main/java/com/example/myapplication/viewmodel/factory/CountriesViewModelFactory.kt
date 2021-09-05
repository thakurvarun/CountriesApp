package com.example.myapplication.ViewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ViewModel.CountriesViewModel

class CountriesViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountriesViewModel::class.java)){
            return CountriesViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}