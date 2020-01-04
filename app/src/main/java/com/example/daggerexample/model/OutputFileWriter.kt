package com.example.daggerexample.model


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject


public class OutputFileWriter  constructor( var context: Context){

    var sharedPreferences: SharedPreferences

   init {
        this.context = context
        sharedPreferences = context.getSharedPreferences("outputFile", MODE_PRIVATE)

    }


    fun getOutputFile(): String {
        return sharedPreferences.getString("output", "")!!
    }

    fun setOutPutFile(outPutFile: String) {
        sharedPreferences.edit().putString("output", outPutFile).apply()
    }
}