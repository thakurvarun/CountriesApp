package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ViewModel.CountriesViewModel
import com.example.myapplication.ViewModel.factory.CountriesViewModelFactory
import com.example.myapplication.adapter.CountriesAdapter
import com.example.myapplication.databinding.ActivityCountriesBinding
import com.example.myapplication.model.countries.Countries
import com.example.myapplication.model.countries.Country
import com.example.myapplication.utils.JsonUtil
import com.google.gson.Gson


class CountriesListActivity : AppCompatActivity() {

    private lateinit var mBindedView: ActivityCountriesBinding
    private var mCountriesJson: Countries? = null
    private var viewModel : CountriesViewModel ?= null
    var recyclerViewAdapter: CountriesAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindedView = ActivityCountriesBinding.inflate(layoutInflater)
        val view = mBindedView.root
        setContentView(view)
        val jsonFileString = JsonUtil.getJsonDataFromAsset(applicationContext, "country.json")
        val gson = Gson()
        //Treating this Will be the Json Response
        mCountriesJson = gson.fromJson(jsonFileString, Countries::class.java)
        val factory = CountriesViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(CountriesViewModel::class.java)

        observeData()
//        initViews()
    }


    /**
     * Init the Views
     */
    private fun initViews( ) {

        mBindedView.rvCountries
    }


    fun observeData(){
        viewModel?.userMutableLiveData?.observe(this, Observer{
            Log.i("data",it.toString())
            recyclerViewAdapter = CountriesAdapter(mCountriesJson!!.countries)
            mBindedView.rvCountries.setLayoutManager(LinearLayoutManager(applicationContext))
            mBindedView.rvCountries.setAdapter(recyclerViewAdapter)
        })
    }


//    var userListUpdateObserver: Observer<ArrayList<Country>> = object : Observer<ArrayList<Country?>?> {
//
//        override fun onChanged(userArrayList: ArrayList<Country?>?) {
//            recyclerViewAdapter = CountriesAdapter(mCountriesJson!!.countries)
//            mBindedView.rvCountries.setLayoutManager(LinearLayoutManager(applicationContext))
//            mBindedView.rvCountries.setAdapter(recyclerViewAdapter)
//        }
//    }


}