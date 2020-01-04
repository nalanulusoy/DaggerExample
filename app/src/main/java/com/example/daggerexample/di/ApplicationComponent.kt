package com.example.daggerexample.di

import android.app.Application
import com.example.daggerexample.DaggerExampleApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.BindsInstance
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,ActivityModule::class,HeroModule::class,ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }



    fun inject(daggerExampleApplication: DaggerExampleApplication)
}