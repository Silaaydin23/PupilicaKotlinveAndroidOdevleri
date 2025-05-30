package com.example.yemekbitirmeodevi.di

import com.example.yemekbitirmeodevi.data.repo.YemeklerRepository
import com.example.yemekbitirmeodevi.retrofit.ApiUtils
import com.example.yemekbitirmeodevi.retrofit.RetrofitClient
import com.example.yemekbitirmeodevi.retrofit.YemeklerDaoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDaoInterface(): YemeklerDaoInterface {
        return RetrofitClient.getClient(ApiUtils.BASE_URL).create(YemeklerDaoInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideYemeklerRepository(yemeklerDao: YemeklerDaoInterface): YemeklerRepository {
        return YemeklerRepository(yemeklerDao)
    }
}

