package com.example.kontaktownia.ui.wybieranie

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kontaktownia.cyfra
import com.example.kontaktownia.databinding.FragmentWybieranieBinding
import com.example.kontaktownia.dzwon

/**
 * Wybieranie fragment
 *
 * @constructor Utworz fragment wybierania
 */
class WybieranieFragment : Fragment() {

    private var _binding: FragmentWybieranieBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        //definicja wartosci z pliku xml
        _binding = FragmentWybieranieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val knefle = listOf(binding.button1, binding.button2, binding.button3, binding.button4, binding.button5, binding.button6, binding.button7,binding.button8,binding.button9,binding.button10,binding.button11)
        //dodanie sluchaczy do przyciskow
        cyfra(knefle, binding.editTextNumber)
        dzwon(binding.imageButton, "", binding.root)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
