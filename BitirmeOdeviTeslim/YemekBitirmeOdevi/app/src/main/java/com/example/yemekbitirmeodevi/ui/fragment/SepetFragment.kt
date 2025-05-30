package com.example.yemekbitirmeodevi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemekbitirmeodevi.databinding.FragmentSepetBinding
import com.example.yemekbitirmeodevi.ui.adapter.SepetAdapter
import com.example.yemekbitirmeodevi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SepetViewModel by viewModels()
    private lateinit var adapter: SepetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadSepetData()
        setupObservers()
        setupButtons()
    }

    private fun setupRecyclerView() {
        binding.rVSepetim.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun loadSepetData() {
        try {
            viewModel.sepettekiYemekleriGetir("sila_aydin")
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Sepet yüklenirken bir hata oluştu: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupObservers() {
        viewModel.sepetYemekleri.observe(viewLifecycleOwner) { yemekler ->
            try {
                adapter = SepetAdapter(requireContext(), yemekler, viewModel)
                binding.rVSepetim.adapter = adapter

                var toplamTutar = 0
                yemekler.forEach { yemek ->
                    toplamTutar += yemek.yemekFiyat * yemek.yemek_siparis_adet
                }
                binding.textViewTutarSepetim.text = "$toplamTutar ₺"
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Sepet güncellenirken bir hata oluştu: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            Toast.makeText(requireContext(), hata, Toast.LENGTH_LONG).show()
        }

        viewModel.silmeDurumu.observe(viewLifecycleOwner) { basarili ->
            if (basarili) {
                loadSepetData()
            }
        }
    }

    private fun setupButtons() {
        binding.buttonSepetOnayla.setOnClickListener {
            Toast.makeText(requireContext(), "Sipariş onaylandı!", Toast.LENGTH_SHORT).show()
            // Burada sipariş onaylama işlemleri yapılabilir
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}