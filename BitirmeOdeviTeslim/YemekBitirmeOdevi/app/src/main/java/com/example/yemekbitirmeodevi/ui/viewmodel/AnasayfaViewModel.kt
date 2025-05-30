package com.example.yemekbitirmeodevi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemekbitirmeodevi.data.entity.Yemekler
import com.example.yemekbitirmeodevi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(
    private val yemeklerRepository: YemeklerRepository
) : ViewModel() {

    private val _yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yemeklerListesi: LiveData<List<Yemekler>> = _yemeklerListesi

    private val _hataMesaji = MutableLiveData<String>()
    val hataMesaji: LiveData<String> = _hataMesaji

    private var tumYemekler: List<Yemekler> = emptyList()

    init {
        tumYemekleriGetir()
    }

    private fun tumYemekleriGetir() {
        viewModelScope.launch {
            try {
                val cevap = yemeklerRepository.tumYemekleriGetir()
                if (cevap.success == 1) {
                    tumYemekler = cevap.yemekler ?: emptyList()
                    _yemeklerListesi.value = tumYemekler
                } else {
                    _hataMesaji.value = "Yemekler yüklenemedi"
                }
            } catch (e: Exception) {
                _hataMesaji.value = "Bir hata oluştu: ${e.message}"
            }
        }
    }

    fun yemekleriFiltrele(aramaKelimesi: String) {
        if (aramaKelimesi.isEmpty()) {
            _yemeklerListesi.value = tumYemekler
        } else {
            val filtrelenmisListe = tumYemekler.filter { yemek ->
                yemek.yemek_adi.lowercase().contains(aramaKelimesi.lowercase())
            }
            _yemeklerListesi.value = filtrelenmisListe
        }
    }
}
