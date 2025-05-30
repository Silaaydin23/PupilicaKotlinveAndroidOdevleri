package com.example.yemekbitirmeodevi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.yemekbitirmeodevi.databinding.FragmentDetayBinding
import com.example.yemekbitirmeodevi.ui.viewmodel.DetayViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetayViewModel by viewModels()
    private val args: DetayFragmentArgs by navArgs()
    private var yemekAdet = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val yemek = args.yemek
        
        binding.textViewAdDetay.text = yemek.yemek_adi
        binding.textViewFiyatDetay.text = "${yemek.yemek_fiyat} ₺"
        binding.textViewAdet.text = yemekAdet.toString()
        
        // İlk açılışta toplam fiyatı göster
        binding.textViewTutarDetay.text = "Toplam: ${yemek.yemek_fiyat * yemekAdet} ₺"

        // Resim yükleme
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(binding.imageViewDetay)

        binding.fabButtonarti.setOnClickListener {
            yemekAdet++
            binding.textViewAdet.text = yemekAdet.toString()
            binding.textViewTutarDetay.text = "Toplam: ${yemek.yemek_fiyat * yemekAdet} ₺"
        }

        binding.fabButtoneksi.setOnClickListener {
            if (yemekAdet > 1) {
                yemekAdet--
                binding.textViewAdet.text = yemekAdet.toString()
                binding.textViewTutarDetay.text = "Toplam: ${yemek.yemek_fiyat * yemekAdet} ₺"
            }
        }

        binding.buttonSepetDetay.setOnClickListener {
            viewModel.sepeteYemekEkle(
                yemek.yemek_adi,
                yemek.yemek_resim_adi,
                yemek.yemek_fiyat,
                yemekAdet,
                "sila_aydin"
            )
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            Toast.makeText(requireContext(), hata, Toast.LENGTH_LONG).show()
        }

        viewModel.sepeteEklemeDurumu.observe(viewLifecycleOwner) { basarili ->
            if (basarili) {
                Toast.makeText(requireContext(), "Yemek sepete eklendi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}