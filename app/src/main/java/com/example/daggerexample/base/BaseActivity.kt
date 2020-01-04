package com.example.daggerexample.base

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager

import android.os.Build

import androidx.core.content.ContextCompat
import android.app.Activity

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity(){

    val activity: Activity
        get() = this


    fun AskPermision(): Boolean {
        var durum = true
        if (ContextCompat.checkSelfPermission(
                activity,
              READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            durum = false
        }
        if (ContextCompat.checkSelfPermission(
                activity,
            WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            durum = false
        }
        return durum
    }

    fun Want_To_Permision() {
        val smsOkumaIzni =
            ContextCompat.checkSelfPermission(activity,READ_EXTERNAL_STORAGE)

        val bellegeYazmaIzni =
            ContextCompat.checkSelfPermission(activity,WRITE_EXTERNAL_STORAGE)
        val izinler = ArrayList<String>()

        if (bellegeYazmaIzni != PackageManager.PERMISSION_GRANTED) {

            izinler.add(WRITE_EXTERNAL_STORAGE)

        }
        if (smsOkumaIzni != PackageManager.PERMISSION_GRANTED) {
            izinler.add(READ_EXTERNAL_STORAGE)
        }
        if (!izinler.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(izinler.toTypedArray(), 200)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            200 -> {
                for (i in permissions.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.e("Permissions", "İzin Verildi: " + permissions[i])

                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Log.e("Permissions", "İzin Reddedildi: " + permissions[i])

                    }
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }


}