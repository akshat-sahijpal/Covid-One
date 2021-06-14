package com.akshatsahijpal.covidone.ui.fragment.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.databinding.FragmentEntryBinding
import kotlinx.android.synthetic.main.fragment_entry.*


class EntryFragment : Fragment(R.layout.fragment_entry) {
    private var _binding: FragmentEntryBinding? = null
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        URLdet.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019")
            )
            startActivity(i)
        }

        _binding = FragmentEntryBinding.bind(view)
        navController = Navigation.findNavController(view)
        clicks()
    }

    private fun clicks() {
        _binding?.plasmBtn?.setOnClickListener {
            navController.navigate(R.id.action_entryFragment_to_plasmaFragment)
        }
        _binding?.contributeBtn?.setOnClickListener {
            navController.navigate(R.id.action_entryFragment_to_contributeDataFragment)
        }
        _binding?.covidSuppliesBtn?.setOnClickListener {
            navController.navigate(R.id.action_entryFragment_to_medSuppliesFragment)
        }
        _binding?.oxyBtn?.setOnClickListener {
            navController.navigate(R.id.action_entryFragment_to_oxygenCenterFragment)
        }
        _binding?.outlinedButton?.setOnClickListener {
            navController.navigate(R.id.action_entryFragment_to_contributeDataFragment)
        }
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}