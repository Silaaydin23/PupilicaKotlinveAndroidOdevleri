package com.example.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.fragment.app.FragmentManager
import com.example.odev4.databinding.FragmentYBinding

class YFragment : Fragment() {
private lateinit var binding: FragmentYBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentYBinding.inflate(inflater,container,false)

        return binding.root
    }

}