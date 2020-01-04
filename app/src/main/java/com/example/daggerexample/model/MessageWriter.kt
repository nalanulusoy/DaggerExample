package com.example.daggerexample.model


import android.content.Context



public class MessageWriter constructor(context : Context) {
   var inputOutputFileOperation: InputOutputFileOperation
  var  context : Context
    init {
        this.context=context
        inputOutputFileOperation=InputOutputFileOperation(context)
    }



    fun writeMessage(message: String) {
        inputOutputFileOperation?.writeOutputFromFile(message)
    }

}