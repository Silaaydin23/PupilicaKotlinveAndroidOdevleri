package com.example.arayuztasarimi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.arayuztasarimi.databinding.FragmentAnasayfaBinding


class AnasayfaFragment : Fragment() {

    private lateinit var binding: FragmentAnasayfaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)

        val urunlerListesi = ArrayList<Urunler>()
        val u1 = Urunler(1,"Sky Classic Denim Açık Mavi Jean ","acik_jean",1399)
        val u2 = Urunler(2,"Sky Classic Denim Gri Jean","siyah_jean",1499)
        val u3 = Urunler(3,"Mavi Logo Baskılı Siyah Tişört","mavi_t",329)
        val u4 = Urunler(4,"Carina Street Beyaz-Pembe-Bej-Lila Kadın Spor Ayakkabı","puma",2239)
        val u5 = Urunler(5,"Chanel Coco Mademoiselle Edp 200 ml Kadın Parfüm","parfum",15899)
        val u6 = Urunler(6,"Stanley Quencher Pipetli Termos 1,18 L Açık Pembe","stanley",1669)
        urunlerListesi.add(u1)
        urunlerListesi.add(u2)
        urunlerListesi.add(u3)
        urunlerListesi.add(u4)
        urunlerListesi.add(u5)
        urunlerListesi.add(u6)

        val urunlerAdapter = UrunlerAdapter(requireContext(),urunlerListesi)
        binding.urunlerRv.adapter = urunlerAdapter
        binding.urunlerRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        return binding.root
    }


}
