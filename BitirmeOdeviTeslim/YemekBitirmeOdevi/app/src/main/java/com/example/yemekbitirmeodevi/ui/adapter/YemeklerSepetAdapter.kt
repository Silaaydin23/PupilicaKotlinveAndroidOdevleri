package com.example.yemekbitirmeodevi.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekbitirmeodevi.data.entity.Yemekler
import com. example. yemekbitirmeodevi. R
import com.example.yemekbitirmeodevi.data.entity.SepetYemekler
import com.example.yemekbitirmeodevi.ui.fragment.AnasayfaFragment
import com.squareup.picasso.Picasso

class YemeklerSepetAdapter(private val mContext: Context, private val yemeklerListe: List<SepetYemekler>):RecyclerView.Adapter<YemeklerSepetAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){
        var cardViewYemekSepet: CardView
        var tVFiyatSepet: TextView
        var tVAdetSepet: TextView
        var imageViewYemekSepet: ImageView

        init {
            cardViewYemekSepet = tasarim.findViewById(R.id.cardViewYemek)
            tVFiyatSepet = tasarim.findViewById(R.id.textViewYemekAdi)
            tVAdetSepet = tasarim.findViewById(R.id.textViewFiyat)
            imageViewYemekSepet = tasarim.findViewById(R.id.imageViewYemek)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.sepet_yemek_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return yemeklerListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListe.get(position)

        holder.tVFiyatSepet.text = yemek.yemekAd

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemekResimAd}"

        Picasso.get().load(url).into(holder.imageViewYemekSepet)



    }
}