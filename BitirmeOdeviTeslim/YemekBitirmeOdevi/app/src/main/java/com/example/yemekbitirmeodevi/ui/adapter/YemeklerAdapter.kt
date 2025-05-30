package com.example.yemekbitirmeodevi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekbitirmeodevi.data.entity.Yemekler
import com.example.yemekbitirmeodevi.R
import com.example.yemekbitirmeodevi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemekbitirmeodevi.ui.LoadingDialog
import com.squareup.picasso.Picasso
import android.os.Handler
import android.os.Looper
import kotlin.random.Random

class YemeklerAdapter(private val mContext: Context, private val yemeklerListe: List<Yemekler>):RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){
        var cardViewYemek: CardView
        var textViewYemekAdi: TextView
        var textViewFiyat: TextView
        var imageViewYemek: ImageView

        init {
            cardViewYemek = tasarim.findViewById(R.id.cardViewYemek)
            textViewYemekAdi = tasarim.findViewById(R.id.textViewYemekAdi)
            textViewFiyat = tasarim.findViewById(R.id.textViewFiyat)
            imageViewYemek = tasarim.findViewById(R.id.imageViewYemek)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.yemek_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return yemeklerListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListe.get(position)

        holder.textViewYemekAdi.text = yemek.yemek_adi
        holder.textViewFiyat.text = "${yemek.yemek_fiyat} ₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        Picasso.get().load(url).into(holder.imageViewYemek)

        holder.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            val navController = Navigation.findNavController(it)
            
            // Loading göster
            LoadingDialog.show(mContext)
            
            // 1-2 saniye arası bekle
            val delayMillis = Random.nextLong(1000, 2000)
            Handler(Looper.getMainLooper()).postDelayed({
                LoadingDialog.dismiss()
                navController.navigate(gecis)
            }, delayMillis)
        }
    }
}