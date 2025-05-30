package com.example.yemekbitirmeodevi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekbitirmeodevi.data.entity.SepetYemekler
import com.example.yemekbitirmeodevi.databinding.SepetYemekTasarimBinding
import com.example.yemekbitirmeodevi.ui.viewmodel.SepetViewModel
import com.squareup.picasso.Picasso

class SepetAdapter(
    private val mContext: Context,
    private val sepetYemeklerListesi: List<SepetYemekler>,
    private val viewModel: SepetViewModel
) : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(val binding: SepetYemekTasarimBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = SepetYemekTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int = sepetYemeklerListesi.size

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = sepetYemeklerListesi[position]
        val binding = holder.binding

        binding.textViewAdSepet.text = yemek.yemekAd
        binding.tVFiyatSepet.text = "${yemek.yemekFiyat} ₺"
        binding.textViewAdetSepet.text = "Adet: ${yemek.yemek_siparis_adet}"
        

        val toplamTutar = yemek.yemekFiyat * yemek.yemek_siparis_adet
        binding.textViewTutarSepet.text = "Toplam: $toplamTutar ₺"


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemekResimAd}"
        Picasso.get().load(url).into(binding.imageViewYemekSepet)


        binding.imageViewSilSepet.setOnClickListener {
            viewModel.sepettenYemekSil(yemek.sepetYemekId, "sila_aydin")
        }
    }
} 