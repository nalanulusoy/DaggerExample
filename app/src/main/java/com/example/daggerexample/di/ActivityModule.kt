package com.example.daggerexample.di

import com.example.daggerexample.MainActivity
import com.example.daggerexample.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule



@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {
    @ContributesAndroidInjector
    fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun baseActivityInjector(): BaseActivity
}