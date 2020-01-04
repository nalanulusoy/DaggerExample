package com.example.daggerexample

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

    lateinit var parser: InputParseOperation


    lateinit var operation: InputOutputFileOperation


    lateinit var writer: MessageWriter


    lateinit var outputFileWriter: OutputFileWriter


    lateinit var hero: Hero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        CheckFilePermision()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            GameRun()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun GameRun() {
        val inputStr = operation!!.readInputFromFile()
        hero = parser!!.getHero(inputStr)
        hero!!.writer = writer
        hero!!.start(parser!!.getRoute(inputStr))

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


    fun setValues(){
        hero= Hero(context = applicationContext)
        operation= InputOutputFileOperation(applicationContext)
        parser= InputParseOperation(applicationContext)
        writer= MessageWriter(applicationContext)
        outputFileWriter= OutputFileWriter(applicationContext)
    }

    fun onClickGameBtn(v: View) {
        txt_result_game.text = outputFileWriter!!.getOutputFile()
        bt_result_show.setVisibility(View.GONE)

        if (hero!!.isAlive) {

            HeroIsAlive()
        } else {
            HeroIsDied()
        }

    }
}
