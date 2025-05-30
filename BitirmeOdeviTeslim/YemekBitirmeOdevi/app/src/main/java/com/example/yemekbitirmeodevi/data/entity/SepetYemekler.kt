package com.example.yemekbitirmeodevi.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SepetYemekler(
    @SerializedName("sepet_yemek_id")
    @Expose var sepetYemekId: Int,
    @SerializedName("yemek_adi")
    @Expose var yemekAd: String,
    @SerializedName("yemek_resim_adi")
    @Expose var yemekResimAd: String,
    @SerializedName("yemek_fiyat")
    @Expose var yemekFiyat: Int,
    @SerializedName("yemek_siparis_adet")
    @Expose var yemek_siparis_adet: Int,
    @SerializedName("kullanici_adi")
    @Expose var kullanici_adi: String
) {
}