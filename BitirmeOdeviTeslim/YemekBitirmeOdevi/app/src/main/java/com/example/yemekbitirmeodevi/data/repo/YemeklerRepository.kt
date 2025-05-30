package com.example.yemekbitirmeodevi.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemekbitirmeodevi.data.entity.Yemekler
import com.example.yemekbitirmeodevi.data.entity.YemeklerCevap
import com.example.yemekbitirmeodevi.data.entity.CRUDCevap
import com.example.yemekbitirmeodevi.data.entity.SepetYemeklerCevap
import com.example.yemekbitirmeodevi.retrofit.YemeklerDaoInterface
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class YemeklerRepository @Inject constructor(
    private val yemeklerDao: YemeklerDaoInterface
) {
    suspend fun tumYemekleriGetir(): YemeklerCevap {
        return yemeklerDao.tumYemekleriGetir().body()!!
    }

    suspend fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ): Response<CRUDCevap> {
        return yemeklerDao.sepeteYemekEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        )
    }

    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): Response<SepetYemeklerCevap> {
        return yemeklerDao.sepettekiYemekleriGetir(kullanici_adi)
    }

    suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String): Response<CRUDCevap> {
        return yemeklerDao.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
    }
}
