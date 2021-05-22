package com.akshatsahijpal.covidone.ui.fragment.unload.oxygen

import android.os.Bundle
import androidx.fragment.app.Fragment
 import android.view.View
 import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
 import com.akshatsahijpal.covidone.databinding.FragmentOxygenCenterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OxygenCenterFragment : Fragment(R.layout.fragment_oxygen_center) {
    private var _binding: FragmentOxygenCenterBinding? = null
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOxygenCenterBinding.bind(view)
        navController = Navigation.findNavController(view)
    }

}