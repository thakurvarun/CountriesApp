package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.viewmodel.CountriesViewModel
import com.example.myapplication.viewmodel.factory.CountriesViewModelFactory
import com.example.myapplication.adapter.CountriesAdapter
import com.example.myapplication.databinding.ActivityCountriesBinding
import com.example.myapplication.model.countries.Countries
import com.example.myapplication.utils.JsonUtil
import com.google.gson.Gson

/**
 * @author : Varun Thakur
 * CountriesListActivity : Screen for displaying the Country List.
 */
class CountriesListActivity : AppCompatActivity() {

    /**
     * Member Variables of the class
     *********** START ******************
     */
    private lateinit var mBindedView: ActivityCountriesBinding
    private var mCountriesJson: Countries? = null
    private var viewModel : CountriesViewModel ?= null
    var recyclerViewAdapter: CountriesAdapter? = null
    /**
     * Member Variables of the class
     *********** END ******************
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View Binding
        mBindedView = ActivityCountriesBinding.inflate(layoutInflater)
        val view = mBindedView.root
        setContentView(view)
        //Load the json from the json file placed in the Assets folder.
        val jsonFileString = JsonUtil.getJsonDataFromAsset(applicationContext, "country.json")
        val gson = Gson()
        //Treating this Will be the Json Response
        mCountriesJson = gson.fromJson(jsonFileString, Countries::class.java)
        //Add a Countries Factory for getting the instance of the current ViewModel
        val factory = CountriesViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(CountriesViewModel::class.java)
        observeData()
    }


    /**
     * Observe Live Data
     * Note : Live Data being one of the most preferred way of storing the data in the app,
     * It can be scaled further to add new functionalities.
     */
    fun observeData(){
        viewModel?.userMutableLiveData?.observe(this, Observer{
            recyclerViewAdapter = CountriesAdapter(mCountriesJson!!.countries)
            mBindedView.rvCountries.setLayoutManager(LinearLayoutManager(applicationContext))
            mBindedView.rvCountries.setAdapter(recyclerViewAdapter)
        })
    }

}