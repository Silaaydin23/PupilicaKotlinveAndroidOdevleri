package com.example.yemekbitirmeodevi.data.datasource

import com.example.yemekbitirmeodevi.data.entity.Yemekler
import com.example.yemekbitirmeodevi.retrofit.YemeklerDaoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var yemeklerDaoInterface: YemeklerDaoInterface) {
    /*
    suspend fun yemekleriYukle() : List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext  yemeklerDaoInterface.tumYemekler()
    }

    suspend fun ara(aramaKelimesi: String) : List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext  yemeklerDaoInterface.ara(aramaKelimesi).kisiler
    }
     */
}