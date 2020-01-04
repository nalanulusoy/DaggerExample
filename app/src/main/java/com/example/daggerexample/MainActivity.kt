package com.example.daggerexample

import android.content.SharedPreferences
import android.os.Build

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.annotation.RequiresApi
import com.example.daggerexample.base.BaseActivity
import com.example.daggerexample.model.*
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var parser: InputParseOperation

    @Inject
    lateinit var operation: InputOutputFileOperation

     @Inject
    lateinit var writer: MessageWriter

    @Inject
    lateinit var outputFileWriter: OutputFileWriter


    @Inject
    lateinit var hero: Hero

    @Inject
    lateinit var  sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CheckFilePermision()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            GameRun()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun GameRun() {
        val inputStr = operation!!.readInputFromFile()
        hero = parser!!.getHero(inputStr)
        hero!!.start(parser!!.getRoute(inputStr),writer,operation,outputFileWriter,sharedPreferences)

    }


    fun HeroIsAlive() {
        img_result.setImageResource(R.drawable.winner_icon)
    }

    fun HeroIsDied() {
        img_result.setImageResource(R.drawable.lost_icon)
    }


    fun CheckFilePermision() {
        if (!AskPermision()) {
            Want_To_Permision()

        }
    }




    fun onClickGameBtn(v: View) {
        txt_result_game.text = outputFileWriter!!.getOutputFile(sharedPreferences)
        bt_result_show.setVisibility(View.GONE)

        if (hero!!.isAlive) {

            HeroIsAlive()
        } else {
            HeroIsDied()
        }

    }
}
