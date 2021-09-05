package com.example.myapplication.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.CountriesViewModel

/**
 * @author Varun Thakur
 *
 * CountriesViewModelFactory : for getting CountriesViewModel instance
 *
 * Note : Can be later used as a Base View Model Factory which can be used to retrieve
 * the instance of other View Models.
 *
 */


class CountriesViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountriesViewModel::class.java)){
            return CountriesViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}