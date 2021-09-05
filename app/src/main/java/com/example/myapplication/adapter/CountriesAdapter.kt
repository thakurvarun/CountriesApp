package com.example.myapplication.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRowCountriesBinding
import com.example.myapplication.model.countries.Country
import java.util.*

/**
 * @author Varun Thakur : thakur.thakurvarun@gmail.com
 *
 */


/**
 * CountriesAdapter :  A RecyclerView Adapter for binding the listing of the countries.
 */
class CountriesAdapter(modelObjectArrayList: ArrayList<Country>?) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    private var countryList: ArrayList<Country> ?= null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowCountriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelObject: Country = countryList!![position]
        holder.countryRowBinding.tvCountry.text = modelObject.name
        holder.countryRowBinding.tvDescription.text = modelObject.description
    }


    override fun getItemCount(): Int {
        return if (countryList == null) 0 else countryList!!.size
    }




    class ViewHolder(countryRowBinding: ItemRowCountriesBinding) :
        RecyclerView.ViewHolder(countryRowBinding.root) {
        val countryRowBinding: ItemRowCountriesBinding = countryRowBinding


    }

    init {
        this.countryList = modelObjectArrayList
    }
}