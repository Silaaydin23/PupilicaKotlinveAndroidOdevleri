package com.example.arayuztasarimi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.navigation.fragment.navArgs
import com.example.arayuztasarimi.databinding.FragmentDetayBinding


class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater,container,false)
        val bundle = DetayFragmentArgs by navArgs()
        val urun = bundle.urunNesnesi

        binding.tvOzellik = urun.ad

        binding.ivUrun.setImageResource(
            resources.getIdentifier(urun.resim,"drawable",requireContext().packageName)
        )

        return binding.root
    }

}