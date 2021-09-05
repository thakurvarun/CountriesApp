package com.example.myapplication.utils

import android.content.Context
import java.io.IOException

/**
 * @author : Varun Thakur.
 */

object JsonUtil {


    /**
     * The function is used to Read the Json from the embedded Json File.
     * @param context - Activity Context
     * @param fileName - a json file from the assets folder.
     */
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}