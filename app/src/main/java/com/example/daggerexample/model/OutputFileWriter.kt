package com.example.daggerexample.model


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject


public class OutputFileWriter @Inject constructor(){






    fun getOutputFile(sharedPreferences:SharedPreferences): String {
        return sharedPreferences.getString("output", "")!!
    }

    fun setOutPutFile(outPutFile: String,sharedPreferences: SharedPreferences) {
        sharedPreferences.edit().putString("output", outPutFile).apply()
    }
}