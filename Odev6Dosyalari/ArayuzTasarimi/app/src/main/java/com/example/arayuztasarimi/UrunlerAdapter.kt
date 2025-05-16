package com.example.arayuztasarimi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.arayuztasarimi.databinding.CardTasarimBinding
import com.example.arayuztasarimi.databinding.FragmentAnasayfaBinding
import com.google.android.material.snackbar.Snackbar

class UrunlerAdapter(var mContext: Context, val urunlerListesi: List<Urunler>)
    : RecyclerView.Adapter<UrunlerAdapter.CardTasarimTutucu>{
    inner class CardTasarimTutucu(var tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        var binding =  CardTasarimBinding.inflate(LayoutInflater.from(mContext) , parent , false)
        return CardTasarimTutucu(binding)
    }
    override fun onBindViewHolder(holder: com.example.arayuztasarimi.UrunlerAdapter.CardTasarimTutucu, position: Int) {
        val urun = urunlerListesi.get(position)
        val t = holder.tasarim
        t.textViewFiyat.text = "${urun.fiyat} ₺"
        t.imageViewUrun.setImageResource(
            mContext.resources.getIdentifier(urun.resim , "drawable" , mContext.packageName)
        )
        t.buttonSepet.setOnClickListener {
            Snackbar.make(it, "${urun.ad} sepete eklendi" , Snackbar.LENGTH_SHORT).show()
        }
        t.cardViewUrun.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(urunNesnesi = urun)
            Navigation.findNavController(it).navigate(gecis)
        }
    }
    override fun getItemCount(): Int {
        return  urunlerListesi.size
    }

}