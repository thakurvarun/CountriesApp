package com.example.myapplication.ViewModel

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.countries.Country


class CountriesViewModel : ViewModel() {
    var countriesLiveData: MutableLiveData<ArrayList<Country>?>
    var countryArrayList: ArrayList<Country>? = null
    val userMutableLiveData: MutableLiveData<ArrayList<Country>?> get() = countriesLiveData

    fun init() {
//        populateList()
        countriesLiveData.value = countryArrayList
    }

//    fun populateList() {
//        val country = Country()
//        user.setTitle("Darknight")
//        user.setDescription("Best rating movie")
//        userArrayList = ArrayList()
//        userArrayList!!.add(user)
//        userArrayList!!.add(user)
//        userArrayList!!.add(user)
//        userArrayList!!.add(user)
//        userArrayList!!.add(user)
//        userArrayList!!.add(user)
//    }

    init {
        countriesLiveData = MutableLiveData<ArrayList<Country>?>()

        // call your Rest API in init method
        init()
    }
}