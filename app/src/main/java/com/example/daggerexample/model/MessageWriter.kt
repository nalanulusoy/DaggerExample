package com.example.daggerexample.model


import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


public class MessageWriter @Inject constructor() {


    fun writeMessage(message: String,outputFileWriter:OutputFileWriter,inputOutputFileOperation: InputOutputFileOperation,sharedPreferences: SharedPreferences) {
        inputOutputFileOperation?.writeOutputFromFile(message,outputFileWriter =outputFileWriter,sharedPreferences = sharedPreferences)
    }

}