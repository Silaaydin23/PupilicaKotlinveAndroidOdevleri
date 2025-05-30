package com.example.yemekbitirmeodevi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemekbitirmeodevi.data.entity.SepetYemekler
import com.example.yemekbitirmeodevi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(
    private val yemeklerRepository: YemeklerRepository
) : ViewModel() {

    private val _sepetYemekleri = MutableLiveData<List<SepetYemekler>>()
    val sepetYemekleri: LiveData<List<SepetYemekler>> = _sepetYemekleri

    private val _hataMesaji = MutableLiveData<String>()
    val hataMesaji: LiveData<String> = _hataMesaji

    private val _silmeDurumu = MutableLiveData<Boolean>()
    val silmeDurumu: LiveData<Boolean> = _silmeDurumu

    fun sepettekiYemekleriGetir(kullanici_adi: String) {
        viewModelScope.launch {
            try {
                val response = yemeklerRepository.sepettekiYemekleriGetir(kullanici_adi)
                if (response.isSuccessful) {
                    response.body()?.let { cevap ->
                        if (cevap.success == 1 && !cevap.sepetYemekler.isNullOrEmpty()) {
                            _sepetYemekleri.value = cevap.sepetYemekler
                        } else {
                            _sepetYemekleri.value = emptyList()
                        }
                    } ?: run {
                        _sepetYemekleri.value = emptyList()
                    }
                } else {
                    _sepetYemekleri.value = emptyList()
                    _hataMesaji.value = "Sepet verisi alınamadı"
                }
            } catch (e: Exception) {
                _sepetYemekleri.value = emptyList()
                if (e.message?.contains("End of input") == true) {
                    // Boş sepet durumu, hata mesajı göstermeye gerek yok
                    _sepetYemekleri.value = emptyList()
                } else {
                    _hataMesaji.value = "Bir hata oluştu: ${e.message}"
                }
            }
        }
    }

    fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        viewModelScope.launch {
            try {
                val response = yemeklerRepository.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
                if (response.isSuccessful) {
                    response.body()?.let { cevap ->
                        if (cevap.success == 1) {
                            _silmeDurumu.value = true
                            // Sepeti güncelle
                            sepettekiYemekleriGetir(kullanici_adi)
                        } else {
                            _hataMesaji.value = "Yemek silinemedi"
                            _silmeDurumu.value = false
                        }
                    } ?: run {
                        _hataMesaji.value = "Silme işlemi başarısız"
                        _silmeDurumu.value = false
                    }
                } else {
                    _hataMesaji.value = "Yemek sepetten silinirken bir hata oluştu"
                    _silmeDurumu.value = false
                }
            } catch (e: Exception) {
                if (e.message?.contains("End of input") == true) {
                    // Silme başarılı, sepet boş
                    _silmeDurumu.value = true
                    _sepetYemekleri.value = emptyList()
                } else {
                    _hataMesaji.value = "Bir hata oluştu: ${e.message}"
                    _silmeDurumu.value = false
                }
            }
        }
    }
}