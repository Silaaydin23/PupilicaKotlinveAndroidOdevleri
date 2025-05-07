package com.example.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.navigation.Navigation
import com.example.odev4.databinding.FragmentABinding

class AFragment : Fragment() {
    private lateinit var binding: FragmentABinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentABinding.inflate(inflater,container,false)

        binding.buttonA.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.aBGecis)
        }

        return binding.root
    }

}