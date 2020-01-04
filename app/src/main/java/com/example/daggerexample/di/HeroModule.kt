package com.example.daggerexample.di

import com.example.daggerexample.model.Hero
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HeroModule {

    @Provides
    @Singleton
   fun provideHero(): Hero {
        return Hero()
    }

}