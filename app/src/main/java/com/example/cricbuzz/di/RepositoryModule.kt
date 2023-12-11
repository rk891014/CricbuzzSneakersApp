package com.example.cricbuzz.di

import com.example.cricbuzz.repository.SneakersRepository
import com.example.cricbuzz.repository.SneakersRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideRepository(sneakersRepositoryImp: SneakersRepositoryImp): SneakersRepository
}