package com.example.yemekbitirmeodevi.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yemekbitirmeodevi.R
import com.example.yemekbitirmeodevi.databinding.FragmentAnasayfaBinding
import com.example.yemekbitirmeodevi.ui.LoadingDialog
import com.example.yemekbitirmeodevi.ui.adapter.YemeklerAdapter
import com.example.yemekbitirmeodevi.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnasayfaViewModel by viewModels()
    private lateinit var adapter: YemeklerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupFabButton()
    }

    private fun setupToolbar() {
        // Toolbar'ı ActionBar olarak ayarla
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarAnaSayfa)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupRecyclerView() {
        binding.rvAnasayfa.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setupObservers() {
        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { yemekler ->
            adapter = YemeklerAdapter(requireContext(), yemekler)
            binding.rvAnasayfa.adapter = adapter
        }

        viewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            Toast.makeText(requireContext(), hata, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupFabButton() {
        binding.fabSepet.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekSepetGecis()
            
            // Loading göster
            LoadingDialog.show(requireContext())
            
            // 1-2 saniye arası bekle
            val delayMillis = Random.nextLong(1000, 2000)
            Handler(Looper.getMainLooper()).postDelayed({
                LoadingDialog.dismiss()
                Navigation.findNavController(it).navigate(gecis)
            }, delayMillis)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.yemekleriFiltrele(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.yemekleriFiltrele(it) }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

