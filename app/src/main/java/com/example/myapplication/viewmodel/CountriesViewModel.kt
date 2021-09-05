package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.countries.Country

/**
 * @author : Varun Thakur
 * CountriesViewModel : A view model class to store the data used for displaying in the list.
 *
 */
class CountriesViewModel : ViewModel() {
    var countriesLiveData: MutableLiveData<ArrayList<Country>?>
    var countryArrayList: ArrayList<Country>? = null
    val userMutableLiveData: MutableLiveData<ArrayList<Country>?> get() = countriesLiveData

    fun init() {
        countriesLiveData.value = countryArrayList
    }


    init {
        countriesLiveData = MutableLiveData<ArrayList<Country>?>()
        // Good to fire the network calls from init()
        init()
    }
}