package com.example.cricbuzz.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AssetManager {
        return appContext.assets
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

}