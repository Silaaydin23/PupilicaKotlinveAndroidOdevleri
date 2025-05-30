package com.example.yemekbitirmeodevi.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SepetYemeklerCevap(
    @SerializedName("sepet_yemekler")
    @Expose var sepetYemekler: List<SepetYemekler>,
    @SerializedName("success")
    @Expose var success: Int
) {
}