package com.example.daggerexample.model


import android.content.Context
import android.content.SharedPreferences
import android.os.Environment

import android.util.Log
import java.io.*
import javax.inject.Inject


public class InputOutputFileOperation @Inject constructor() {

    private val inputFile = "res/raw/input"

    fun readInputFromFile(): String {
        val input = this.javaClass.classLoader!!.getResourceAsStream(inputFile)
        var allText: String? =null
        try {
        allText = input.bufferedReader().use(BufferedReader::readText)
        } catch (e: IOException) {
            Log.e("InputFile", "Input File Exception")
        }

        return allText!!
    }

    fun writeOutputFromFile(message: String,outputFileWriter:OutputFileWriter,sharedPreferences: SharedPreferences) {
        outputFileWriter?.setOutPutFile(message,sharedPreferences =sharedPreferences )

        try {
           var fileName: String? =Environment.getDataDirectory().absolutePath+ File.separator+"output.txt"
            File(fileName).bufferedWriter().use { out -> out.write(message) }

        } catch (e: IOException) {
            Log.e("OutputFile", "Output File Exception")
        }

    }

}

