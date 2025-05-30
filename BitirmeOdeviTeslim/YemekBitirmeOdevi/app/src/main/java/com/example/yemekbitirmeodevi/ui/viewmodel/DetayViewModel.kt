package com.example.yemekbitirmeodevi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemekbitirmeodevi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(
    private val yemeklerRepository: YemeklerRepository
) : ViewModel() {

    private val _sepeteEklemeDurumu = MutableLiveData<Boolean>()
    val sepeteEklemeDurumu: LiveData<Boolean> = _sepeteEklemeDurumu

    private val _hataMesaji = MutableLiveData<String>()
    val hataMesaji: LiveData<String> = _hataMesaji

    fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        viewModelScope.launch {
            try {
                val response = yemeklerRepository.sepeteYemekEkle(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet,
                    kullanici_adi
                )
                
                if (response.isSuccessful) {
                    _sepeteEklemeDurumu.value = true
                } else {
                    _hataMesaji.value = "Sepete eklenirken bir hata oluştu"
                    _sepeteEklemeDurumu.value = false
                }
            } catch (e: Exception) {
                _hataMesaji.value = "Bir hata oluştu: ${e.message}"
                _sepeteEklemeDurumu.value = false
            }
        }
    }
}